package com.community.controller;

import com.community.utils.CheckCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CheckCode {
	@RequestMapping(value="/checkCode")
	public void checkCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");

		//设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);

		CheckCodeUtils randomValidateCode = new CheckCodeUtils();
		try {
			randomValidateCode.getRandcode(request, response);//输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
