package com.community.service.impl;

import com.community.dao.ManagerDao;
import com.community.domain.Manager;
import com.community.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;

	@Override
	public Boolean backLoginValidation(String mnumber, String mpassword) {
		List<Manager> managerList = managerDao.backLoginValidation(mnumber,mpassword);
		if (managerList.size()>1 || managerList.size()<1){
			return false;
		}
		return true;
	}
}
