package com.community.controller;

import com.community.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 更改控制器
 */
@Controller
@RequestMapping("/change")
public class ChangeController {

	@Autowired
	private ChangeService changeService;

	//更改头像改变
	@RequestMapping("/head_image")
	public String head_image(@CookieValue(name = "community") String community,
							 HttpServletRequest request,
							 MultipartFile image) throws IOException {
		if(!image.isEmpty()){
			String pathTar = request.getSession().getServletContext().getRealPath("/userpictures");
			String pathLoc ="F:\\JAVAXX\\community_ssm\\src\\main\\webapp\\userpictures";

			File pathTarFile = new File(pathTar);//如果文件夹不存在,则创建(支持tomcat单独部署了)
			if (!pathTarFile.exists()){
				pathTarFile.mkdirs();
			}

			String fileName = community + ".png";
			new Thread(new Runnable() {
				public void run() {
					try {
						image.transferTo(new File(pathTar, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} ).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						image.transferTo(new File(pathLoc, fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} ).start();
			//更改student表中的imgpath
			changeService.changeImageName(community);
		}
		return "redirect:/index";
	}

	//更改个性签名
	@RequestMapping("/signature")
	@ResponseBody
	public String signature(@CookieValue(name = "community") String community, @RequestParam("signature") String signature) {
		System.out.println("个性签名更改:" + community);
		changeService.changeSignatureBySnumber(community, signature);
		return "success";
	}

	//用户活动签到状态改变,需要cookie(用户snumber)和anumber
	@RequestMapping("/signing")
	public String signing(@CookieValue(name = "community") String snumber, @RequestParam("anumber") String anumber, HttpServletResponse response) throws IOException {
		//如果cookie为null或者cookie为"",跳转到登录页面
		if (snumber == null || "".equals(snumber)) {
			response.sendRedirect("/pages/userlogin.jsp");
		}

		System.out.println("用户活动签到状态,需要cookie:" + snumber + "和anumber:" + anumber);
		changeService.changeSigningStatus(snumber, anumber);
		return "redirect:/show/activityPage?anumber="+anumber;
	}

	//用户活动报名,需要cookie(用户snumber)和anumber
	@RequestMapping("/activityEnroll")
	public String activityEnroll(@CookieValue(name = "community", required = false) String snumber, @RequestParam(value = "anumber") String anumber) throws IOException {

		if ("".equals(snumber) || snumber == null) {
			return "redirect:/pages/userlogin.jsp";
		}

		System.out.println(snumber + "正在报名:" + anumber);
		changeService.addActivityEnrollBySnumberAndAnumber(snumber, anumber);

		return "redirect:/show/activityPage?anumber=" + anumber;
	}

	//用户参加社团(通过snumber和cnumber)
	@RequestMapping("/addJoinClub")
	public String addJoinClub(@CookieValue(name = "community", required = false) String snumber, @RequestParam(value = "cnumber") String cnumber) {

		if ("".equals(snumber) || snumber == null) {
			return "redirect:/pages/userlogin.jsp";
		}

		System.out.println(snumber + ":加入社团:" + cnumber);
		changeService.addJoinClubSnumberAndCnumber(snumber, cnumber);
		return "redirect:/show/communityPage?cnumber=" + cnumber;
	}

	//add 用户留言,需要cookie(用户snumber)和留言内容mecontext
	@RequestMapping("/addMessage")
	@ResponseBody
	public String addMessage(@CookieValue(name = "community", required = false) String snumber, @RequestParam(value = "mecontext") String mecontext, HttpServletResponse response) throws IOException {

		if ("".equals(snumber) || snumber == null) {
			response.sendRedirect("/pages/userlogin.jsp");
		}

		System.out.println("正在留言:" + snumber);

		changeService.addMessage(snumber, mecontext);

		return "success";
	}

	//退出社团(snumber和cookie(snumber))
	@RequestMapping("/quitCommunity")
	@ResponseBody
	public String quitCommunity(@CookieValue(name = "community") String snumber, @RequestParam(value = "cnumber") String cnumber) {

		System.out.println(snumber + ":退出社团:" + cnumber);
		changeService.quitCommunityBySnumberAndCnumber(snumber, cnumber);

		return "success";
	}

	//更改密码(原密码和新密码和snumber)
	@RequestMapping("/changePassword")
	public String changePassword(@CookieValue(name = "community") String snumber, @RequestParam(value = "new_pwd") String npassword, @RequestParam(value = "again_pwd") String apassword, @RequestParam(value = "old_pwd") String opassword, HttpServletResponse response, HttpServletRequest request) {

		System.out.println(snumber + ":更改密码为:" + npassword);

		if (!npassword.equals(apassword)) {//如果两次密码不相等
			request.setAttribute("msg", "两次密码不一样");
			return "/show/changepwd";
		}

		Boolean sign = changeService.loginValidation(snumber, opassword);//登录校验

		if (sign) {//旧密码正确密码相同
			changeService.changePasswordBySnumberAndSpassword(snumber, npassword);
			request.setAttribute("msg", "密码修改成功");
			return "/show/changepwd";
		} else {//旧密码错误
			request.setAttribute("msg", "旧密码错误");
			return "/show/changepwd";
		}

	}

}
