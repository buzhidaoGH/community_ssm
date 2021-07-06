package com.community.domain;


import com.community.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 中间表
 */
public class StagingTable implements Serializable{

	private String anumber;//活动编码
	private String snumber;//学生编码
	private String cnumber;//社团编码

	private Integer signing;//参加活动签到标记 0为未签到;1为签到 默认0
	private String signingStr;

	private Date jointime;//加入社团时间标记
	private String jointimeStr;//加入社团时间标记
	private Integer joinrole;//社团中的角色标记 0社员,1社长 默认0
	private String joinroleStr;

	public String getAnumber() {
		return anumber;
	}

	public void setAnumber(String anumber) {
		this.anumber = anumber;
	}

	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public String getCnumber() {
		return cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public String getSigningStr() {
		return signing==0?"未签到":"已签到";
	}

	public void setSigningStr(String signingStr) {
		this.signingStr = signingStr;
	}

	public String getJointimeStr() {
		return DateUtils.date2String(jointime,"yyyy-MM-dd");
	}

	public void setJointimeStr(String jointimeStr) {
		this.jointimeStr = jointimeStr;
	}

	public String getJoinroleStr() {
		return joinrole==0?"社员":"社长";
	}

	public void setJoinroleStr(String joinroleStr) {
		this.joinroleStr = joinroleStr;
	}

	public Integer getSigning() {
		return signing;
	}

	public void setSigning(Integer signing) {
		this.signing = signing;
	}

	public Date getJointime() {
		return jointime;
	}

	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}

	public Integer getJoinrole() {
		return joinrole;
	}

	public void setJoinrole(Integer joinrole) {
		this.joinrole = joinrole;
	}
}
