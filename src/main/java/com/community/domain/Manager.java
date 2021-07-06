package com.community.domain;

import java.io.Serializable;

public class Manager implements Serializable {
	private Integer mid;
	private String mnumber;
	private String mpassword;
	private String mname;
	private String telephone;
	private String address;
	private Integer level;

	@Override
	public String toString() {
		return "Manager{" +
				"mid=" + mid +
				", mnumber='" + mnumber + '\'' +
				", mpassword='" + mpassword + '\'' +
				", mname='" + mname + '\'' +
				", telephone='" + telephone + '\'' +
				", address='" + address + '\'' +
				", level=" + level +
				'}';
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMnumber() {
		return mnumber;
	}

	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
