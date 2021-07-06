package com.community.domain;

import com.community.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Community implements Serializable {
	private Integer cid;
	private String cnumber;
	private String cname;
	private Date creationtime;//社团创建时间
	private String creationtimeStr;
	private String introduction; //社团介绍
	private String notice; //公告
	private String imgpath;//logo图片路径
	private Integer peoples;//成员人数

	//一个社团可以对应多个社长
	private List<Student> students;

	//一个社团可以对应多个成员
	private List<Student> peopleList;

	@Override
	public String toString() {
		return "Community{" +
				"cid=" + cid +
				", cnumber='" + cnumber + '\'' +
				", cname='" + cname + '\'' +
				", creationtime=" + creationtime +
				", creationtimeStr='" + creationtimeStr + '\'' +
				", introduction='" + introduction + '\'' +
				", notice='" + notice + '\'' +
				", imgpath='" + imgpath + '\'' +
				", peoples=" + peoples +
				", students=" + students +
				", peopleList=" + peopleList +
				'}';
	}

	public List<Student> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(List<Student> peopleList) {
		this.peopleList = peopleList;
	}

	public Integer getPeoples() {
		return peoples;
	}

	public void setPeoples(Integer peoples) {
		this.peoples = peoples;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getCreationtimeStr() {
		return DateUtils.date2String(creationtime,"yyyy-MM-dd");
	}

	public void setCreationtimeStr(String creationtimeStr) {
		this.creationtimeStr = creationtimeStr;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCnumber() {
		return cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

}
