package com.community.service;

import com.community.domain.*;

import java.util.List;

public interface MainService {
	News noticeOne();

	List<Manager> managerSimple();

	List<Activity> latestActivityData();

	List<News> latestNewsData();

	List<Community> findAllCommunityData();

	Community findFirstCommunityData();

	Student findPresidentByCnumber(String cnumber);

	List<Activity> findAllActivity(int page,int size,String atitle);

	List<News> findAllNews(int page, int size, String ntitle);

	Boolean loginValidation(String snumber, String spassword);

	Student findPersonalCenterBySnumber(String snumber);

	List<StagingTable> findSigningBySnumber(String snumber);

	List<StagingTable> findPresidentBySnumber();
}
