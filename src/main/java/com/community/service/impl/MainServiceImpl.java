package com.community.service.impl;

import com.community.dao.*;
import com.community.domain.*;
import com.community.service.MainService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Manager逻辑层(管理员)
@Service("mainService")
public class MainServiceImpl implements MainService {

	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private NewsDao newsDao;
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private CommunityDao communityDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StagingTableDao stagingTableDao;

	//校园公告
	@Override
	public News noticeOne() {
		return newsDao.noticeOne();
	}

	//首页管理员简单联系方式
	@Override
	public List<Manager> managerSimple() {
		return managerDao.managerSimple();
	}

	//最新六条活动
	@Override
	public List<Activity> latestActivityData() {
		return activityDao.latestActivityData();
	}

	//最新六条新闻
	@Override
	public List<News> latestNewsData() {
		return newsDao.latestNewsData();
	}

	//所有社团简单信息
	@Override
	public List<Community> findAllCommunityData() {
		return communityDao.findAllCommunityData();
	}

	//查询第一个社团
	@Override
	public Community findFirstCommunityData() {
		return communityDao.findFirstCommunityData();
	}

	//通过社团cnumber查询社长
	@Override
	public Student findPresidentByCnumber(String cnumber) {
		return studentDao.findPresidentByCnumber(cnumber);
	}

	//所有社团活动
	@Override
	public List<Activity> findAllActivity(int page, int size,String atitle) {
		//参数pageNum是页码值,pageSize是表示条数,必须写在调用dao的前面,前一行
		PageHelper.startPage(page,size);
		return activityDao.findAllActivity(atitle);
	}

	@Override
	public List<News> findAllNews(int page, int size, String ntitle) {
		//参数pageNum是页码值,pageSize是表示条数,必须写在调用dao的前面,前一行
		PageHelper.startPage(page,size);
		return newsDao.findAllNews(ntitle);
	}

	@Override
	public Boolean loginValidation(String snumber, String spassword) {
		List<Student> studentList = studentDao.loginValidation(snumber,spassword);
		if (studentList.size()>1 || studentList.size()<1){
			return false;
		}
		return true;
	}

	@Override
	public Student findPersonalCenterBySnumber(String snumber) {
		return studentDao.findPersonalCenterBySnumber(snumber);
	}

	@Override
	public List<StagingTable> findSigningBySnumber(String snumber) {
		return stagingTableDao.findSigningBySnumber(snumber);
	}

	@Override
	public List<StagingTable> findPresidentBySnumber() {
		return stagingTableDao.findPresidentBySnumber();
	}
}
