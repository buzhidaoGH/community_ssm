package com.community.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
	private Integer sid;
	private String snumber;
	private String spassword;
	private String sname;
	private Integer sgender; //0为女;1为男
	private String sgenderStr;
	private String telephone;
	private String collegeClass;
	private Date register;
	private String srole;//角色
	private String imgpath;//头像路径
	private String signature;//个性签名

	//一个学生多个社团
	private List<Community> communities;
	//一个学生参加多个活动
	private List<Activity> activities;
	//一个学生多条留言
	private List<Message> messages;

	@Override
	public String toString() {
		return "Student{" +
				"sid=" + sid +
				", snumber='" + snumber + '\'' +
				", spassword='" + spassword + '\'' +
				", sname='" + sname + '\'' +
				", sgender=" + sgender +
				", sgenderStr='" + sgenderStr + '\'' +
				", telephone='" + telephone + '\'' +
				", collegeClass='" + collegeClass + '\'' +
				", register=" + register +
				", srole='" + srole + '\'' +
				", imgpath='" + imgpath + '\'' +
				", signature='" + signature + '\'' +
				", communities=" + communities +
				", activities=" + activities +
				", messages=" + messages +
				'}';
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSgenderStr() {
		return sgender==0?"女":"男";
	}

	public void setSgenderStr(String sgenderStr) {
		this.sgenderStr = sgenderStr;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public String getSpassword() {
		return spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getSgender() {
		return sgender;
	}

	public void setSgender(Integer sgender) {
		this.sgender = sgender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCollegeClass() {
		return collegeClass;
	}

	public void setCollegeClass(String collegeClass) {
		this.collegeClass = collegeClass;
	}

	public Date getRegister() {
		return register;
	}

	public void setRegister(Date register) {
		this.register = register;
	}

	public String getSrole() {
		return srole;
	}

	public void setSrole(String srole) {
		this.srole = srole;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
}
