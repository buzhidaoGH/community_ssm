package com.community.controller;

import com.community.domain.*;
import com.community.service.MainService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//主要页面的直接调用,门户
@Controller
public class MainController {

	@Autowired
	private MainService mainService;

	//首页自动加载数据(空壳框架数据)
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {//跳转(有可能还需要设置cookie来判断登录?)
		/*Cookie[] cookies = request.getCookies();
		if (cookies!=null){
			for(Cookie cookie : cookies){
				System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());
			}
		}*/
		System.out.println("主页:主页index->main.jsp");
		ModelAndView mv = new ModelAndView();
		//填充校园公告(新闻的第一条)
		News newsOne = mainService.noticeOne();
		//填充下方管理员部分信息
		List<Manager> managers = mainService.managerSimple();

		mv.addObject("managers",managers);
		mv.addObject("newsOne",newsOne);
		mv.setViewName("main");

		return mv;
	}

	//首页base数据
	@RequestMapping("/pages/base")//base.jsp填充
	public ModelAndView base() {
		System.out.println("基础首页:/pages/base");
		ModelAndView mv = new ModelAndView();
		//填充base的内容
		//1.填充最新的6条活动和链接
		List<Activity> activities = mainService.latestActivityData();
		//2.填充最新的6条新闻和链接
		List<News> newsList = mainService.latestNewsData();
		//3.填充所有的社团图片地址和社团名称和社团链接()
		List<Community> communities = mainService.findAllCommunityData();

		mv.addObject("activities",activities);
		mv.addObject("newsList",newsList);
		mv.addObject("communities",communities);
		mv.setViewName("/pages/base");

		return mv;
	}

	//社团列表数据
	@RequestMapping("/pages/group_list")//group_list.jsp填充
	public ModelAndView group_list() {
		System.out.println("社团列表:/pages/group_list");
		ModelAndView mv = new ModelAndView();
		//填充首页社团列表
		List<Community> communities = mainService.findAllCommunityData();
		//填充第一个社团
		Community community = mainService.findFirstCommunityData();
		//获取第一个社团的cnumber然后通过中间表查看社长
		Student president = mainService.findPresidentByCnumber(community.getCnumber());

		mv.addObject("president",president);
		mv.addObject("communities",communities);
		mv.addObject("communityOne",community);
		mv.setViewName("/pages/group_list");
		return mv;
	}

	//社团活动分页的所有列表(1~6),支持分页,和模糊查询标题
	@RequestMapping("/pages/group_active")//group_active.jsp填充
	public ModelAndView group_active(@RequestParam(name = "page",defaultValue="1")int page,@RequestParam(name = "size",defaultValue="6")int size,@RequestParam(name = "atitle",defaultValue="")String atitle){
		System.out.println("社团("+page+":"+size+":"+atitle+")活动:/pages/group_active");
		ModelAndView mv = new ModelAndView();

		//填充活动列表
		List<Activity> activityList = mainService.findAllActivity(page,size,atitle);
		//pageInfo就是分页Bean
		PageInfo pageInfo = new PageInfo(activityList);
		mv.addObject("atitle",atitle);
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("/pages/group_active");

		return mv;
	}

	//校园新闻分页的所有列表(1~6),支持分页,和模糊查询标题
	@RequestMapping("/pages/news_list")//news_list.jsp填充
	public ModelAndView news_list(@RequestParam(name = "page",defaultValue="1")int page,@RequestParam(name = "size",defaultValue="6")int size,@RequestParam(name = "ntitle",defaultValue="")String ntitle){
		System.out.println("新闻("+page+":"+size+":"+ntitle+")列表:/pages/group_active");
		ModelAndView mv = new ModelAndView();

		//填充新闻列表
		List<News> newsList = mainService.findAllNews(page,size,ntitle);
		//pageInfo就是分页Bean
		PageInfo pageInfo = new PageInfo(newsList);
		mv.addObject("ntitle",ntitle);
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("/pages/news_list");

		return mv;
	}

	//留言板页面
	@RequestMapping("/pages/message_list")
	public ModelAndView message_list(){
		System.out.println("留言板需要cookie(账号):/pages/message_list");
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/pages/message_list");

		return mv;
	}

	//个人中心页面
	@RequestMapping("/pages/personal_center")
	public ModelAndView personal_center(@CookieValue(value = "community",defaultValue = "0") String snumber, HttpServletResponse response) throws IOException {
		System.out.println("个人中心使用snumber:/pages/personal_center");
		//获取cookie(community)
		//System.out.println(snumber);
		if ("0".equals(snumber)){//如果没有cookie,重定向到登录页面
			response.sendRedirect("/pages/userlogin.jsp");
		}

		ModelAndView mv = new ModelAndView();
		//填充个人信息(根据snumber)
		//这个是最难的
		Student student = mainService.findPersonalCenterBySnumber(snumber);
		//填充我参加的社团:学生和社团表关联了:多对多

		//System.out.println(student);
		//填充我参加的活动:学生和活动表关联了:多对多

		//个人留言一对多

		//填充判断签到状态,先通过该用户snumber参加的所有活动的中间表
		List<StagingTable> stagingTableList= mainService.findSigningBySnumber(snumber);

		//填充判断是否为该社长(所有社的社长,获取cnumber和snumber页面比对,通过student_community的joinrole)
		//List<StagingTable> presidentList = mainService.findPresidentBySnumber();
		//mv.addObject("presidentList",presidentList);

		mv.addObject("stagingTableList",stagingTableList);
		mv.addObject("student",student);
		mv.setViewName("/pages/personal_center");

		return mv;
	}

	//系统简介页面
	/*@RequestMapping("/pages/introduction")
	public ModelAndView introduction(){
		System.out.println("系统简介:/pages/introduction");
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/pages/pages/introduction.html");

		return mv;
	}*/

	/**
	 * 方式一：方法返回的URI（相对路径）中加上"redirect:"前缀，声明要重定向到该地址
	 *        "redirect:"后面跟着的是"/"和不跟着"/"是不一样的：
	 *        1) "redirect:"后面跟着"/"： 说明该URI是相对于项目的Context ROOT的相对路径
	 *        2) "redirect:"后面没有跟着"/"： 说明该URI是相对于当前路径
	 */
	//门户登录页面处理
	@RequestMapping(path = "/pages/user_login",method = RequestMethod.POST)
	public String userlogin(Student student, HttpServletResponse response, HttpServletRequest request) throws IOException {//用户登录处理
		Boolean sign = mainService.loginValidation(student.getSnumber(),student.getSpassword());//登录校验
		if (sign){//登录成功
			System.out.println("登录成功,设置了cookie");
			Cookie cookie = new Cookie("community", student.getSnumber());
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
			//设置maxAge过期时间 默认 -1  保存在浏览器缓存中 关闭浏览器失效
			// 正整数设置过期时间7天，单位为s
			//cookie.setMaxAge(7*24*60*60);
			// 0 立即失效  cookie的销毁
			//cookie.setMaxAge(0);
			return "redirect:/index";
		}
		else {//登录失败
			request.setAttribute("msg","账号或者密码错误");
			return "userlogin";
		}
	}

	//门户退出登录处理(清除cookie)
	@RequestMapping(path = "/pages/userLogout")
	public String userLogout(HttpServletResponse response){
		Cookie cookie = new Cookie("community",null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		return "redirect:/index";
	}


}
