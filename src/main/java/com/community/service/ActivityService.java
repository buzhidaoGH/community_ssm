package com.community.service;

import com.community.domain.Activity;
import com.community.domain.StagingTable;

import java.util.List;

public interface ActivityService {
	Activity findActivityByAnumber(String anumber);

	StagingTable findSigningByAnumberAndSnumber(String anumber, String snumber);

	Integer findAllCountActivity();

	List<Activity> findAllActivityList(int page, int size, String atitle);

	void createNewOneActivity(Activity activity, String starttime, String endtime);

	void changeActivityByAnumber(Activity activity);
}
