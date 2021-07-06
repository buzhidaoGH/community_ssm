package com.community.domain;

import com.community.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Activity implements Serializable {
	private Integer aid;
	private String anumber;
	private String atitle;
	private String organizer;
	private String alocation;
	private String telephone;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date starttime;//开始时间
	private String starttimeStr;
	private String starttimeLongStr;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endtime;//结束时间
	private String endtimeStr;
	private String endtimeLongStr;
	private String details;
	private Integer peoples;//参与人数
	private String varStatus;//状态

	//一个活动可以有多个学生参加
	private List<Student> students;

	@Override
	public String toString() {
		return "Activity{" +
				"aid=" + aid +
				", anumber='" + anumber + '\'' +
				", atitle='" + atitle + '\'' +
				", organizer='" + organizer + '\'' +
				", alocation='" + alocation + '\'' +
				", telephone='" + telephone + '\'' +
				", starttime=" + starttime +
				", starttimeStr='" + starttimeStr + '\'' +
				", starttimeLongStr='" + starttimeLongStr + '\'' +
				", endtime=" + endtime +
				", endtimeStr='" + endtimeStr + '\'' +
				", endtimeLongStr='" + endtimeLongStr + '\'' +
				", details='" + details + '\'' +
				", peoples=" + peoples +
				", varStatus='" + varStatus + '\'' +
				", students=" + students +
				'}';
	}

	public String getVarStatus() {
		Date nowTime = new Date();
		return DateUtils.belongCalendar(nowTime,starttime,endtime);
	}

	public void setVarStatus(String varStatus) {
		this.varStatus = varStatus;
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

	public String getStarttimeLongStr() {
		return DateUtils.date2String(starttime,"yyyy-MM-dd HH:mm");
	}

	public void setStarttimeLongStr(String starttimeLongStr) {
		this.starttimeLongStr = starttimeLongStr;
	}

	public String getEndtimeLongStr() {
		return DateUtils.date2String(endtime,"yyyy-MM-dd HH:mm");
	}

	public void setEndtimeLongStr(String endtimeLongStr) {
		this.endtimeLongStr = endtimeLongStr;
	}

	public String getStarttimeStr() {
		return DateUtils.date2String(starttime,"MM-dd");
	}

	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
	}

	public String getEndtimeStr() {
		return DateUtils.date2String(endtime,"MM-dd");
	}

	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAnumber() {
		return anumber;
	}

	public void setAnumber(String anumber) {
		this.anumber = anumber;
	}

	public String getAtitle() {
		return atitle;
	}

	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getAlocation() {
		return alocation;
	}

	public void setAlocation(String alocation) {
		this.alocation = alocation;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
