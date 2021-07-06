package com.community.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 */
public class DateUtils {

	//String pattern 表示日期格式
	/*
	SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm");
    SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//等价于now.toLocaleString()
    SimpleDateFormat myFmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
    SimpleDateFormat myFmt4=new SimpleDateFormat("一年中的第 D 天 一年中第w个星期 一月中第W个星期 在一天中k时 z时区");
	 */

	//日期转换成字符串
	public static String date2String(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String format = sdf.format(date);
		return format;
	}

	//字符串转换成日期
	public static Date string2Date(String str,String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date parse = sdf.parse(str);
		return parse;
	}

	//判断日期区间和当前时间的 活动未开始 活动进行中 活动已结束
	public static String belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.before(begin))
		{
			return "未开始";
		}
		if(date.after(begin) && date.before(end)){
			return "进行中";
		}
		if(date.after(end)){
			return "已结束";
		}
		return "暂无";
	}

}
