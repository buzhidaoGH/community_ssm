package com.community.controller;

import com.community.domain.*;
import com.community.service.*;
import com.community.utils.CheckCodeUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 后台控制器
 */
@Controller
@RequestMapping("/backs")
public class BackController {

	@Autowired
	private ManagerService managerService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private MainService mainService;
	@Autowired
	private StagingTableService stagingTableService;

	//后台登录路径,成功设置cookie
	@RequestMapping("/backLogin")
	public String backLogin(@RequestParam("username") String mnumber, @RequestParam("password") String mpassword,@RequestParam("checkcode") String checkcode, HttpServletRequest request, HttpServletResponse response) {

		/*System.out.println(request.getSession().getAttribute("randomcode_key"));
		System.out.println(checkcode);*/
		if (request.getSession().getAttribute("randomcode_key")==null){
			request.setAttribute("msg", "验证码失效");
			return "backlogin";
		}

		if (!request.getSession().getAttribute("randomcode_key").toString().equalsIgnoreCase(checkcode)){
			request.setAttribute("msg", "验证码错误");
			return "backlogin";
		}

		request.getSession().removeAttribute("randomcode_key");

		Boolean sign = managerService.backLoginValidation(mnumber, mpassword);//登录校验
		if (sign) {//登录成功
			System.out.println("登录成功,设置了cookie");
			Cookie cookie = new Cookie("back_community", mnumber);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
			return "redirect:/backs/backindex";
		} else {//登录失败
			request.setAttribute("msg", "账号或者密码错误");
			return "backlogin";
		}
	}

	//后台退出登录处理(清除cookie)
	@RequestMapping(path = "/backLogOut")
	public String backLogout(HttpServletResponse response) {
		System.out.println("清除cookie成功");
		Cookie cookie = new Cookie("back_community", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		return "redirect:/pages/backlogin.jsp";
	}

	//后台首页加载
	@RequestMapping("/backindex")
	public ModelAndView backindex(@CookieValue(name = "back_community", required = false) String mnumber) {
		ModelAndView mv = new ModelAndView();
		//System.out.println(mnumber);
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		System.out.println("后台首页进入填充:/backs/backindex");
		//填充各种总数
		Integer countCommunity = communityService.findAllCountCommunity();
		Integer countActivity = activityService.findAllCountActivity();
		Integer countNews = newsService.findAllCountNews();
		Integer countStudent = studentService.findAllCountStudent();
		Integer countMessage = messageService.findAllCountMessage();
		//填充当前轮播的指定公告()
		News newsOne = newsService.noticeOneNow();

		mv.addObject("countCommunity", countCommunity);
		mv.addObject("countActivity", countActivity);
		mv.addObject("countNews", countNews);
		mv.addObject("countStudent", countStudent);
		mv.addObject("countMessage", countMessage);
		mv.addObject("newsOne", newsOne);

		mv.setViewName("/backs/backindex");
		return mv;
	}

	//用户管理:支持分页和多项模糊查询
	@RequestMapping("/management_user")
	public ModelAndView management_user(@CookieValue(name = "back_community", required = false) String mnumber,
										@RequestParam(name = "page", defaultValue = "1") int page,
										@RequestParam(name = "size", defaultValue = "6") int size,
										@RequestParam(name = "snumber", defaultValue = "") String snumber,
										@RequestParam(name = "sname", defaultValue = "") String sname) {
		ModelAndView mv = new ModelAndView();
		//System.out.println(mnumber);
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		System.out.print("后台用户管理:size:" + size + " page:" + page + ":/backs/management_user");
		System.out.println(" snumber:" + snumber + " sname:" + sname);

		List<Student> studentList = studentService.findAllStudentList(page, size, snumber, sname);
		//pageInfo就是分页Bean
		PageInfo pageInfo = new PageInfo(studentList);

		mv.addObject("pageInfo", pageInfo);
		mv.addObject("snumber", snumber);
		mv.addObject("sname", sname);
		mv.setViewName("/backs/management_user");
		return mv;
	}

	//社团管理:支持分页和多项模糊查询
	@RequestMapping("/management_community")
	public ModelAndView management_community(@CookieValue(name = "back_community", required = false) String mnumber,
											 @RequestParam(name = "page", defaultValue = "1") int page,
											 @RequestParam(name = "size", defaultValue = "2") int size,
											 @RequestParam(name = "cname", defaultValue = "") String cname) {
		ModelAndView mv = new ModelAndView();
		//System.out.println(mnumber);
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		System.out.print("后台社团管理:size:" + size + " page:" + page + ":/backs/management_community");
		System.out.println(" cname:" + cname);

		List<Community> communityList = communityService.findAllCommunityList(page, size, cname);
		//pageInfo就是分页Bean
		PageInfo pageInfo = new PageInfo(communityList);

		mv.addObject("pageInfo", pageInfo);
		mv.addObject("cname", cname);
		mv.setViewName("/backs/management_community");
		return mv;
	}

	//活动管理:支持分页和多项模糊查询
	@RequestMapping("/management_activity")
	public ModelAndView management_activity(@CookieValue(name = "back_community", required = false) String mnumber,
											@RequestParam(name = "page", defaultValue = "1") int page,
											@RequestParam(name = "size", defaultValue = "6") int size,
											@RequestParam(name = "atitle", defaultValue = "") String atitle) {
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		System.out.print("后台活动管理:size:" + size + " page:" + page + ":/backs/management_activity");
		System.out.println(" atitle:" + atitle);


		List<Activity> activityList = activityService.findAllActivityList(page, size, atitle);
		//pageInfo就是分页Bean
		PageInfo pageInfo = new PageInfo(activityList);

		mv.addObject("pageInfo", pageInfo);
		mv.addObject("atitle", atitle);
		mv.setViewName("/backs/management_activity");
		return mv;
	}

	//新闻管理:支持分页和多项模糊查询
	@RequestMapping("/management_news")
	public ModelAndView management_news(@CookieValue(name = "back_community", required = false) String mnumber,
										@RequestParam(name = "page", defaultValue = "1") int page,
										@RequestParam(name = "size", defaultValue = "6") int size,
										@RequestParam(name = "ntitle", defaultValue = "") String ntitle) {
		ModelAndView mv = new ModelAndView();
		//System.out.println(mnumber);
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		System.out.print("后台新闻管理:size:" + size + " page:" + page + ":/backs/management_news");
		System.out.println(" ntitle:" + ntitle);

		List<News> newsList = newsService.findAllNewsList(page, size, ntitle);
		//pageInfo就是分页Bean
		PageInfo pageInfo = new PageInfo(newsList);

		mv.addObject("pageInfo", pageInfo);
		mv.addObject("ntitle", ntitle);
		mv.setViewName("/backs/management_news");
		return mv;
	}


	//留言管理:支持分页和多项模糊查询
	@RequestMapping("/management_message")
	public ModelAndView management_message(@CookieValue(name = "back_community", required = false) String mnumber,
										   @RequestParam(name = "page", defaultValue = "1") int page,
										   @RequestParam(name = "size", defaultValue = "6") int size,
										   @RequestParam(name = "peopleid", defaultValue = "") String peopleid,
										   @RequestParam(name = "status", defaultValue = "") String status) {
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		System.out.print("后台留言管理:size:" + size + " page:" + page + ":/backs/management_message");
		System.out.println(" peopleid:" + peopleid);

		List<Message> messageList = messageService.findAllMessageList(page, size, peopleid, status);
		//pageInfo就是分页Bean
		PageInfo pageInfo = new PageInfo(messageList);

		mv.addObject("pageInfo", pageInfo);
		mv.addObject("peopleid", peopleid);
		mv.addObject("status", status);
		mv.setViewName("/backs/management_message");
		return mv;
	}

	//以下是后台逻辑处理了,增删改查backs的数据库数据

	//查阅留言
	@RequestMapping("/checkMessage")
	public ModelAndView checkMessage(@CookieValue(name = "back_community", required = false) String mnumber,
									 @RequestParam(name = "meid") Integer meid,
									 @RequestParam(name = "size") String size,
									 @RequestParam(name = "page") String page,
									 @RequestParam(name = "peopleid", defaultValue = "") String peopleid,
									 @RequestParam(name = "status", defaultValue = "") String status) {
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		System.out.print("留言查看操作:/backs/checkMessage");

		messageService.checkMessageByPeopleId(meid);

		mv.setViewName("redirect:/backs/management_message?size=" + size + "&page=" + page + "&peopleid=" + peopleid + "&status=" + status);
		return mv;
	}

	//删除留言
	@RequestMapping("/deleteMessage")
	public ModelAndView deleteMessage(@CookieValue(name = "back_community", required = false) String mnumber,
									  @RequestParam(name = "meid") Integer meid,
									  @RequestParam(name = "size") String size,
									  @RequestParam(name = "page") String page,
									  @RequestParam(name = "peopleid", defaultValue = "") String peopleid,
									  @RequestParam(name = "status", defaultValue = "") String status) {
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		System.out.print("删除留言" + meid + ":/backs/deleteMessage");

		messageService.deleteMessageByPeopleId(meid);

		mv.setViewName("redirect:/backs/management_message?size=" + size + "&page=" + page + "&peopleid=" + peopleid + "&status=" + status);
		return mv;
	}

	//查阅个人信息
	@RequestMapping("/show_student")
	public ModelAndView show_student(@CookieValue(name = "back_community", required = false) String mnumber,
									 @RequestParam(name = "snumber") String snumber) {
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		//填充个人信息(根据snumber)
		//超级难
		Student student = mainService.findPersonalCenterBySnumber(snumber);
		//填充判断签到状态,先通过该用户snumber参加的所有活动的中间表
		List<StagingTable> stagingTableList = mainService.findSigningBySnumber(snumber);
		mv.addObject("stagingTableList", stagingTableList);
		mv.addObject("student", student);
		mv.setViewName("/backs/show_student");

		return mv;
	}

	//查询社团信息
	@RequestMapping("/show_community")
	public ModelAndView show_community(@CookieValue(name = "back_community", required = false) String mnumber,
									   @RequestParam(name = "cnumber") String cnumber) {
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}

		Community community = communityService.findOneCommunityByCnumber(cnumber);
		mv.addObject("community", community);
		mv.setViewName("/backs/show_community");
		return mv;
	}

	//踢出一人出某个社团
	@RequestMapping("/quit_community")
	public ModelAndView quit_community(@CookieValue(name = "back_community", required = false) String mnumber,
									   @RequestParam(name = "cnumber") String cnumber,
									   @RequestParam(name = "snumber") String snumber) {
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		stagingTableService.quitCommunityByCnumberAndSnumber(cnumber, snumber);
		mv.setViewName("redirect:/backs/show_community?cnumber=" + cnumber);

		return mv;
	}

	//更改社团的简介和公告by cnumber
	@RequestMapping("/change_communityNandI")
	public ModelAndView change_communityNandI(@CookieValue(name = "back_community", required = false) String mnumber,
											  @RequestParam(name = "cnumber") String cnumber,
											  @RequestParam(name = "introduction") String introduction,
											  @RequestParam(name = "notice") String notice) {
		System.out.println("更改社团的简介和公告by cnumber:/change_communityNandI");
		//System.out.println(introduction+":"+notice);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		communityService.changeCommunityNoticeIntroduction(cnumber, notice, introduction);
		mv.setViewName("redirect:/backs/show_community?cnumber=" + cnumber);

		return mv;
	}

	//更改社团的logo图片
	@RequestMapping("/change_communityLogoImage")
	public ModelAndView change_communityLogoImage(@CookieValue(name = "back_community", required = false) String mnumber,
												  @RequestParam(name = "cnumber") String cnumber,
												  MultipartFile imageLogo,
												  HttpServletRequest request) throws IOException {
		System.out.println("上传更改社团logo:" + cnumber);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		if (!imageLogo.isEmpty()) {
			String pathTar = request.getSession().getServletContext().getRealPath("/grouppictures");
			String pathLoc = "F:\\JAVAXX\\community_ssm\\src\\main\\webapp\\grouppictures";
			//判断,该路径是否存在
			File pathTarFile = new File(pathTar);//如果文件夹不存在,则创建(支持tomcat单独部署了)
			if (!pathTarFile.exists()){
				pathTarFile.mkdirs();
			}

			String fileName = cnumber + ".png";
			new Thread(new Runnable() {
				public void run() {
					try {
						imageLogo.transferTo(new File(pathTar, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						imageLogo.transferTo(new File(pathLoc, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			communityService.changeCommunityImage(cnumber);
		}


		mv.setViewName("redirect:/backs/show_community?cnumber=" + cnumber);
		return mv;
	}

	//更改社团的首页图片
	@RequestMapping("/change_communityPosterImage")
	public ModelAndView change_communityPosterImage(@CookieValue(name = "back_community", required = false) String mnumber,
													@RequestParam(name = "cnumber") String cnumber,
													MultipartFile imagePoster,
													HttpServletRequest request) throws IOException {
		System.out.println("上传更改社团Poster:" + cnumber);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		if (!imagePoster.isEmpty()) {
			String pathTar = request.getSession().getServletContext().getRealPath("/grouppictures");
			String pathLoc = "F:\\JAVAXX\\community_ssm\\src\\main\\webapp\\grouppictures";
			//判断,该路径是否存在
			File pathTarFile = new File(pathTar);//如果文件夹不存在,则创建(支持tomcat单独部署了)
			if (!pathTarFile.exists()){
				pathTarFile.mkdirs();
			}
			String fileName = "poster_" + cnumber + ".png";
			new Thread(new Runnable() {
				public void run() {
					try {
						imagePoster.transferTo(new File(pathTar, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						imagePoster.transferTo(new File(pathLoc, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			communityService.changeCommunityImage(cnumber);
		}
		mv.setViewName("redirect:/backs/show_community?cnumber=" + cnumber);
		return mv;
	}


	//创建社团
	@RequestMapping("/new_community")
	public ModelAndView new_community(@CookieValue(name = "back_community", required = false) String mnumber,
									  Community community,
									  @RequestParam("snumber") String snumber,
									  MultipartFile imageLogo,
									  HttpServletRequest request) {
		System.out.println("创建社团:" + community.getCnumber());
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		if (!imageLogo.isEmpty()) {
			String pathTar = request.getSession().getServletContext().getRealPath("/grouppictures");
			String pathLoc = "F:\\JAVAXX\\community_ssm\\src\\main\\webapp\\grouppictures";
			//判断,该路径是否存在

			File pathTarFile = new File(pathTar);//如果文件夹不存在,则创建(支持tomcat单独部署了)
			if (!pathTarFile.exists()){
				pathTarFile.mkdirs();
			}

			String fileName = community.getCnumber() + ".png";
			new Thread(new Runnable() {
				public void run() {
					try {
						imageLogo.transferTo(new File(pathTar, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						File fileSource = new File(pathTar+"/poster_0.png");
						File fileDest = new File(pathTar+"/poster_"+fileName);
						FileUtils.copyFile(fileSource,fileDest);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						File fileSource = new File(pathLoc+"\\poster_0.png");
						File fileDest = new File(pathLoc+"/poster_"+fileName);
						FileUtils.copyFile(fileSource,fileDest);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						imageLogo.transferTo(new File(pathLoc, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} else {
			mv.setViewName("/backs/new_community");
			return mv;
		}
		communityService.createCommunity(community);
		stagingTableService.setupPresidentAndCommunity(snumber, community.getCnumber());

		mv.addObject("cnumber", community.getCnumber());
		mv.addObject("cname", community.getCname());
		mv.setViewName("/backs/new_community");
		return mv;
	}

	//删除社团
	@RequestMapping("/delete_community")
	public ModelAndView delete_community(@CookieValue(name = "back_community", required = false) String mnumber,
										 @RequestParam("cnumber")String cnumber,
										 HttpServletRequest request){
		System.out.println("删除社团:" + cnumber);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		//先删除中间表关联
		stagingTableService.deleteCommunityBycnumber(cnumber);
		//再删除社团表内容表
		communityService.deleteCommunityBycnumber(cnumber);
		String pathTar = request.getSession().getServletContext().getRealPath("/grouppictures");
		String pathLoc = "F:\\JAVAXX\\community_ssm\\src\\main\\webapp\\grouppictures";
		String fileName = cnumber + ".png";
		new Thread(new Runnable() {
			public void run() {
				try {
					File fileTar = new File(pathTar+fileName);
					if (fileTar.isFile() && fileTar.exists()) {
						fileTar.delete();
					}
					File fileLoc = new File(pathLoc+fileName);
					if (fileLoc.isFile() && fileLoc.exists()) {
						fileLoc.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					File fileTar = new File(pathTar+"poster_"+fileName);
					if (fileTar.isFile() && fileTar.exists()) {
						fileTar.delete();
					}
					File fileLoc = new File(pathLoc+"poster_"+fileName);
					if (fileLoc.isFile() && fileLoc.exists()) {
						fileLoc.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		//有斜杠就是根目录,没有就是相对路径
		mv.setViewName("redirect:/backs/management_community");
		return mv;
	}

	//创建新活动
	@RequestMapping("/new_activity")
	public ModelAndView new_activity(@CookieValue(name = "back_community", required = false) String mnumber,
									 Activity activity,
									 @Param("starttimeF") String starttimeF,
									 @Param("starttimeB") String starttimeB,
									 @Param("endtimeF") String endtimeF,
									 @Param("endtimeB") String endtimeB) {
		System.out.print("创建活动:" + activity.getAnumber());
		String starttime = starttimeF + " " + starttimeB;
		String endtime = endtimeF + " " + endtimeB;
		System.out.println(" " + starttime + "和" + endtime);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		if (activity.getAnumber() != null && activity.getAnumber().length() > 0) {
			activityService.createNewOneActivity(activity, starttime, endtime);
			mv.addObject("msg", activity.getAnumber() + "号创建活动成功");
		} else {
			mv.addObject("msg", "创建失败");
		}
		mv.setViewName("/backs/new_active");

		return mv;
	}

	//编辑更改活动信息
	@RequestMapping("/change_activity")
	public ModelAndView change_activity(@CookieValue(name = "back_community", required = false) String mnumber,
										Activity activity) {
		System.out.println("编辑活动:" + activity.getAnumber() + "号");
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}

		activityService.changeActivityByAnumber(activity);

		mv.setViewName("redirect:/backs/show_activity?anumber=" + activity.getAnumber());
		return mv;
	}

	//更改宣传图
	@RequestMapping("/change_activityImg")
	public ModelAndView change_activity(@CookieValue(name = "back_community", required = false) String mnumber,
												  @RequestParam(name = "anumber") String anumber,
												  MultipartFile activityImg,
												  HttpServletRequest request) throws IOException {
		System.out.println("更改活动宣传图:" + anumber);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		if (!activityImg.isEmpty()) {
			String pathTar = request.getSession().getServletContext().getRealPath("/activityImages");
			String pathLoc = "F:\\JAVAXX\\community_ssm\\src\\main\\webapp\\activityImages\\";
			//判断,该路径是否存在
			File pathTarFile = new File(pathTar);//如果文件夹不存在,则创建(支持tomcat单独部署了)
			if (!pathTarFile.exists()){
				pathTarFile.mkdirs();
			}
			File pathLocFile = new File(pathLoc);//如果文件夹不存在,则创建(支持tomcat单独部署了)
			if (!pathLocFile.exists()){
				pathLocFile.mkdirs();
			}
			String fileName = anumber + ".png";
			new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println("进来了Tar");
						activityImg.transferTo(new File(pathTar, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println("进来了Loc");
						activityImg.transferTo(new File(pathLoc, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		mv.setViewName("redirect:/backs/show_activity?anumber=" + anumber);
		return mv;
	}


	//查询单个活动页面
	@RequestMapping("/show_activity")
	public ModelAndView show_activity(@CookieValue(name = "back_community", required = false) String mnumber,
									  @RequestParam("anumber") String anumber) {
		System.out.println("查询单个活动页面:" + anumber);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}

		Activity activity = activityService.findActivityByAnumber(anumber);
		mv.addObject("activity", activity);
		mv.setViewName("/backs/show_activity");
		return mv;
	}

	//发布新新闻
	@RequestMapping("/new_news")
	public ModelAndView new_news(@CookieValue(name = "back_community", required = false) String mnumber,
								 News news) {
		System.out.println("发布新闻:" + news.getNnumber());
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}

		if (news.getNnumber() != null && news.getNnumber().length() > 0) {
			newsService.createNewOnenews(news);
			mv.addObject("msg", news.getNnumber() + "号新闻发布成功");
		} else {
			mv.addObject("msg", "创建失败");
		}
		mv.setViewName("/backs/new_news");
		return mv;
	}

	//查询单个新闻页面
	@RequestMapping("/show_news")
	public ModelAndView show_news(@CookieValue(name = "back_community", required = false) String mnumber,
								  @RequestParam("nnumber") String nnumber) {
		System.out.println("查询单个新闻页面:" + nnumber);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}

		News news = newsService.findOneNewsByNnumber(nnumber);
		mv.addObject("news", news);
		mv.setViewName("/backs/show_news");
		return mv;
	}

	//编辑更改新闻信息
	@RequestMapping("/change_news")
	public ModelAndView change_news(@CookieValue(name = "back_community", required = false) String mnumber,
									News news) {
		System.out.println("编辑新闻:" + news.getNnumber() + "号");
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}
		newsService.changeNewsByNnumber(news);
		mv.setViewName("redirect:/backs/show_news?nnumber=" + news.getNnumber());
		return mv;
	}

	//编辑更改轮播公告
	@RequestMapping("/change_notice")
	public ModelAndView change_notice(@CookieValue(name = "back_community", required = false) String mnumber,
									  @Param("ncontent") String ncontent) {
		System.out.println("编辑更改轮播公告");
		//System.out.println(ncontent);
		ModelAndView mv = new ModelAndView();
		if ("".equals(mnumber) || mnumber == null) {
			mv.setViewName("redirect:/pages/backlogin.jsp");
			return mv;
		}

		newsService.changeNoticeMain(ncontent);

		mv.setViewName("redirect:/backs/backindex");
		return mv;
	}

}
