package com.community.controller;

import com.community.domain.News;
import com.community.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * News 新闻web
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@RequestMapping("/findAll")
	public String findAll(Model model){
		System.out.println("表现层MVC:查询所有账户");
		List<News> list = newsService.findAll();
		model.addAttribute("list",list);
		return "list";
	}
}
