package com.community.service;

public interface StagingTableService {
	Boolean findExistSnumberAndCnumber(String snumber, String cnumber);

	void quitCommunityByCnumberAndSnumber(String cnumber, String snumber);

	void setupPresidentAndCommunity(String snumber, String cnumber);

	void deleteCommunityBycnumber(String cnumber);
}
