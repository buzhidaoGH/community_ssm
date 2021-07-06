package com.community.controller;

import com.community.domain.Community;
import com.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 社团控制的web springMVC视图控制器
 */
@Controller
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	@RequestMapping("/simple")
	@ResponseBody
	public Community communitySimple(@RequestParam("cnumber") String cnumber) {
		//System.out.println(cnumber);
		Community community = communityService.findOneCommunityData(cnumber);
		return community;
	}
}
