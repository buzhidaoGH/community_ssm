package com.community.controller;

import com.community.domain.Student;
import com.community.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 学生控制的web springMVC视图控制器
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	//通过社团编号查找社长
	@RequestMapping(value="/findPresidentByCnumber",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findPresidentByCnumber(@RequestParam("cnumber") String cnumber) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Student president = studentService.findPresidentByCnumber(cnumber);
		//System.out.println(president.getSname());
		return mapper.writeValueAsString(president.getSname());
	}

	@RequestMapping("/register")
	public String studentRegister(HttpServletResponse response,
								  Student student,
								  @RequestParam("apassword")String apassword){
		System.out.println(student.getSnumber()+":注册");
		System.out.println("性别:"+student.getSgenderStr());
		if (student.getSnumber()!=""||apassword==student.getSpassword()&&student.getSpassword()!=""){
			studentService.studentRegister(student);
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
		}else {
			return "redirect:/pages/userregister.jsp";
		}
	}
}
