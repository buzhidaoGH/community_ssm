package com.community.domain;

import com.community.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻实体类
 */
public class News implements Serializable {
	private Integer nid;
	private String nnumber;
	private String ntitle;
	private String nauthor;
	private Date ntime; //发布时间
	private String ntimeStr;
	private String ntimeLongStr;
	private String ncontent;

	@Override
	public String toString() {
		return "News{" +
				"nid=" + nid +
				", nnumber='" + nnumber + '\'' +
				", ntitle='" + ntitle + '\'' +
				", nauthor='" + nauthor + '\'' +
				", ntime=" + ntime +
				", ntimeStr='" + ntimeStr + '\'' +
				", ntimeLongStr='" + ntimeLongStr + '\'' +
				", ncontent='" + ncontent + '\'' +
				'}';
	}

	public String getNtimeLongStr() {
		return DateUtils.date2String(ntime,"yyyy-MM-dd HH:mm");
	}

	public void setNtimeLongStr(String ntimeLongStr) {
		this.ntimeLongStr = ntimeLongStr;
	}

	public String getNtimeStr() {
		return DateUtils.date2String(ntime,"MM-dd");
	}

	public void setNtimeStr(String ntimeStr) {
		this.ntimeStr = ntimeStr;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public String getNnumber() {
		return nnumber;
	}

	public void setNnumber(String nnumber) {
		this.nnumber = nnumber;
	}

	public String getNauthor() {
		return nauthor;
	}

	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}

	public Date getNtime() {
		return ntime;
	}

	public void setNtime(Date ntime) {
		this.ntime = ntime;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
}
