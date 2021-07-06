package com.community.service.impl;

import com.community.dao.MessageDao;
import com.community.dao.StagingTableDao;
import com.community.dao.StudentDao;
import com.community.domain.Student;
import com.community.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("changeService")
public class ChangeServiceImpl implements ChangeService {

	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StagingTableDao stagingTableDao;
	@Autowired
	private MessageDao messageDao;

	@Override
	public void changeImageName(String community) {
		studentDao.changeImgpathBySnumber(community);
	}

	@Override
	public void changeSignatureBySnumber(String community,String signature) {
		studentDao.changeSignatureBySnumber(community,signature);
	}

	@Override
	public void changeSigningStatus(String snumber, String anumber) {
		stagingTableDao.changeSigningStatus(snumber,anumber);
	}

	@Override
	public void addMessage(String snumber, String mecontext) {
		messageDao.addMessage(snumber,mecontext);
	}

	@Override
	public void addActivityEnrollBySnumberAndAnumber(String snumber, String anumber) {
		stagingTableDao.addActivityEnrollBySnumberAndAnumber(snumber,anumber);
	}

	@Override
	public void quitCommunityBySnumberAndCnumber(String snumber, String cnumber) {
		stagingTableDao.quitCommunityBySnumberAndCnumber(snumber,cnumber);
	}

	@Override
	public Boolean loginValidation(String snumber, String opassword) {
		List<Student> studentList = studentDao.loginValidation(snumber,opassword);
		if (studentList.size()>1 || studentList.size()<1){
			return false;
		}
		return true;
	}

	@Override
	public void changePasswordBySnumberAndSpassword(String snumber, String npassword) {
		studentDao.changePasswordBySnumberAndSpassword(snumber,npassword);
	}

	@Override
	public void addJoinClubSnumberAndCnumber(String snumber, String cnumber) {
		stagingTableDao.addJoinClubSnumberAndCnumber(snumber,cnumber);
	}
}
