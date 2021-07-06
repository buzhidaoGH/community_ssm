package com.community.service;

import com.community.domain.Community;
import com.community.domain.News;

import java.util.List;

public interface CommunityService {
	Community findOneCommunityData(String cnumber);

	Community findOneCommunityByCnumber(String cnumber);

	Integer findAllCountCommunity();

	List<Community> findAllCommunityList(int page, int size, String cname);

	void changeCommunityNoticeIntroduction(String cnumber, String notice, String introduction);

	void changeCommunityImage(String cnumber);

	void createCommunity(Community community);

	void deleteCommunityBycnumber(String cnumber);
}
