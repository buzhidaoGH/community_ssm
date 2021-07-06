package com.community.service.impl;

import com.community.dao.ActivityDao;
import com.community.dao.StagingTableDao;
import com.community.domain.Activity;
import com.community.domain.StagingTable;
import com.community.service.ActivityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动service层;逻辑处理层
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private StagingTableDao stagingTableDao;

	@Override
	public Activity findActivityByAnumber(String anumber) {
		return activityDao.findActivityByAnumber(anumber);
	}

	@Override
	public StagingTable findSigningByAnumberAndSnumber(String anumber, String snumber) {
		return stagingTableDao.findSigningByAnumberAndSnumber(anumber,snumber);
	}

	@Override
	public Integer findAllCountActivity() {
		return activityDao.findAllCountActivity();
	}

	@Override
	public List<Activity> findAllActivityList(int page, int size, String atitle) {
		PageHelper.startPage(page,size);
		return activityDao.findAllActivityList(atitle);
	}

	@Override
	public void createNewOneActivity(Activity activity, String starttime, String endtime) {
		activityDao.createNewOneActivity(activity,starttime,endtime);
	}

	@Override
	public void changeActivityByAnumber(Activity activity) {
		activityDao.changeActivityByAnumber(activity);
	}

}
