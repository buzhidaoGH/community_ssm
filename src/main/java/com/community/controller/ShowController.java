package com.community.controller;

import com.community.domain.Activity;
import com.community.domain.Community;
import com.community.domain.News;
import com.community.domain.StagingTable;
import com.community.service.ActivityService;
import com.community.service.CommunityService;
import com.community.service.NewsService;
import com.community.service.StagingTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * 信息展示控制器
 */
@Controller
@RequestMapping("/show")
public class ShowController {

	@Autowired
	private ActivityService activityService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private StagingTableService stagingTableService;

	//activityPage页面展示 活动页面
	@RequestMapping("/activityPage")
	public ModelAndView activityPage(@RequestParam("anumber") String anumber, @CookieValue(name = "community",defaultValue = "0") String snumber){

		System.out.println("activityPage活动页面展示:"+anumber);
		ModelAndView mv = new ModelAndView();
		Activity activity = activityService.findActivityByAnumber(anumber);

		//显示签到状态
		StagingTable stagingTable = activityService.findSigningByAnumberAndSnumber(anumber,snumber);

		mv.addObject("stagingTable",stagingTable);
		mv.addObject("activity",activity);
		mv.setViewName("/show/activityPage");
		return mv;
	}

	//communityPage页面展示 社团页面 通过by cnumber
	@RequestMapping("/communityPage")
	public ModelAndView communityPage(@RequestParam("cnumber") String cnumber,@CookieValue(name = "community",defaultValue = "0") String snumber){
		System.out.println("communityPage社团页面展示:"+cnumber);
		ModelAndView mv = new ModelAndView();

		Community community = communityService.findOneCommunityByCnumber(cnumber);

		//判断用户snumber是否已经加入团cnumber中(student_community)
		Boolean dataExist = stagingTableService.findExistSnumberAndCnumber(snumber,cnumber);
		System.out.println(dataExist);
		//System.out.println(community);
		mv.addObject("dataExist",dataExist);
		mv.addObject("community",community);
		mv.setViewName("/show/communityPage");

		return mv;
	}

	//newsPage页面展示 新闻页面 通过by nnumber
	@RequestMapping("/newsPage")
	public ModelAndView newsPage(@RequestParam("nnumber") String nnumber){
		System.out.println("newsPage新闻页面展示:"+nnumber);
		ModelAndView mv = new ModelAndView();

		News news = newsService.findOneNewsByNnumber(nnumber);

		mv.addObject("news",news);
		mv.setViewName("/show/newsPage");

		return mv;
	}
}
