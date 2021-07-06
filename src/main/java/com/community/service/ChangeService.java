package com.community.service;

public interface ChangeService {
	void changeImageName(String community);

	void changeSignatureBySnumber(String community,String signature);

	void changeSigningStatus(String snumber, String anumber);

	void addMessage(String snumber, String mecontext);

	void addActivityEnrollBySnumberAndAnumber(String snumber, String anumber);

	void quitCommunityBySnumberAndCnumber(String snumber, String cnumber);

	Boolean loginValidation(String snumber, String opassword);

	void changePasswordBySnumberAndSpassword(String snumber, String npassword);

	void addJoinClubSnumberAndCnumber(String snumber, String cnumber);
}
