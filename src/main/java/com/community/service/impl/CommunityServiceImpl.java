package com.community.service.impl;

import com.community.dao.CommunityDao;
import com.community.domain.Community;
import com.community.domain.News;
import com.community.service.CommunityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	private CommunityDao communityDao;

	@Override
	public Community findOneCommunityData(String cnumber) {
		return communityDao.findOneCommunityData(cnumber);
	}

	@Override
	public Community findOneCommunityByCnumber(String cnumber) {
		return communityDao.findOneCommunityByCnumber(cnumber);
	}

	@Override
	public Integer findAllCountCommunity() {
		return communityDao.findAllCountCommunity();
	}

	@Override
	public List<Community> findAllCommunityList(int page, int size, String cname) {
		PageHelper.startPage(page,size);
		return communityDao.findAllCommunityList(cname);
	}

	@Override
	public void changeCommunityNoticeIntroduction(String cnumber, String notice, String introduction) {
		communityDao.changeCommunityNoticeIntroduction(cnumber,notice,introduction);
	}

	@Override
	public void changeCommunityImage(String cnumber) {
		communityDao.changeCommunityImage(cnumber);
	}

	@Override
	public void createCommunity(Community community) {
		communityDao.createCommunity(community);
	}

	@Override
	public void deleteCommunityBycnumber(String cnumber) {
		communityDao.deleteCommunityBycnumber(cnumber);
	}

}
