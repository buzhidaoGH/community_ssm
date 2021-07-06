package com.community.service.impl;

import com.community.dao.StagingTableDao;
import com.community.service.StagingTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stagingTableService")
public class StagingTableServiceImpl implements StagingTableService {
	@Autowired
	private StagingTableDao stagingTableDao;

	@Override
	public Boolean findExistSnumberAndCnumber(String snumber, String cnumber) {
		return stagingTableDao.findExistSnumberAndCnumber(snumber,cnumber);
	}

	@Override
	public void quitCommunityByCnumberAndSnumber(String cnumber, String snumber) {
		stagingTableDao.quitCommunityBySnumberAndCnumber(snumber,cnumber);
	}

	@Override
	public void setupPresidentAndCommunity(String snumber, String cnumber) {
		stagingTableDao.setupPresidentAndCommunity(snumber,cnumber);
	}

	@Override
	public void deleteCommunityBycnumber(String cnumber) {
		stagingTableDao.deleteCommunityBycnumber(cnumber);
	}
}
