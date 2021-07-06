package com.community.domain;

import com.community.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private Integer meid;
	private String peopleid;//snumber
	private String mecontext;//内容
	private Date metime;
	private String metimeStr;//字符串时间
	private Integer status;
	private String statusStr; //0未查看,1已查看

	//一对一
	private String sname;

	@Override
	public String toString() {
		return "Message{" +
				"meid=" + meid +
				", peopleid='" + peopleid + '\'' +
				", mecontext='" + mecontext + '\'' +
				", metime=" + metime +
				", metimeStr='" + metimeStr + '\'' +
				", status=" + status +
				", statusStr='" + statusStr + '\'' +
				", sname='" + sname + '\'' +
				'}';
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		return status==0?"未查看":"已查看";
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getMetimeStr() {
		return DateUtils.date2String(metime,"yyyy-MM-dd HH:mm:ss");
	}

	public void setMetimeStr(String metimeStr) {
		this.metimeStr = metimeStr;
	}

	public Integer getMeid() {
		return meid;
	}

	public void setMeid(Integer meid) {
		this.meid = meid;
	}

	public String getPeopleid() {
		return peopleid;
	}

	public void setPeopleid(String peopleid) {
		this.peopleid = peopleid;
	}

	public String getMecontext() {
		return mecontext;
	}

	public void setMecontext(String mecontext) {
		this.mecontext = mecontext;
	}

	public Date getMetime() {
		return metime;
	}

	public void setMetime(Date metime) {
		this.metime = metime;
	}
}
