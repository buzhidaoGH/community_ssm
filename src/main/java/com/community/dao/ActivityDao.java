package com.community.dao;

import com.community.domain.Activity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//活动dao层
@Repository
public interface ActivityDao {

	//查询最新六条活动
	@Select("SELECT anumber,atitle,starttime FROM activity ORDER BY aid DESC LIMIT 6")
	public List<Activity> latestActivityData();

	//查询所有社团活动
	@Select("SELECT anumber,atitle,organizer,alocation,starttime,endtime FROM activity WHERE atitle LIKE '%${atitle}%'")
	public List<Activity> findAllActivity(@Param("atitle") String atitle);

	//查询每个学生参加的所有活动,通过snumber(学生number)
	@Select("SELECT anumber,atitle,organizer,telephone FROM activity WHERE anumber IN (SELECT anumber FROM student_activity WHERE snumber=#{snumber})")
	List<Activity> findBySnumber(@Param("snumber") String snumber);

	//查询活动详情,通过anumber,并且通过anumber查询参与活动的人数
	@Select("select * from activity where anumber=#{anumber}")
	@Results({
			@Result(id = true, property = "aid", column = "aid"),
			@Result(property = "anumber", column = "anumber"),
			@Result(property = "peoples", column = "anumber",
					javaType = java.lang.Integer.class,
					one = @One(select = "com.community.dao.ActivityDao.findActivityPeoplesByAnumber")),
			@Result(property = "students", column = "anumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.StudentDao.findPeopleListByAnumber"))
	})
	Activity findActivityByAnumber(@Param("anumber") String anumber);

	//计算一个活动参加的人数(by student_activity)
	@Select("SELECT COUNT(*) FROM student_activity WHERE anumber=#{anumber}")
	Integer findActivityPeoplesByAnumber(@Param("anumber") String anumber);

	//查询所有活动的总数
	@Select("SELECT COUNT(*) FROM activity")
	Integer findAllCountActivity();

	//查询所有活动;支持分页和模糊查询
	@Select("SELECT * FROM activity WHERE atitle LIKE CONCAT('%',#{atitle},'%')")
	@Results({
			@Result(id = true, property = "aid", column = "aid"),
			@Result(property = "anumber", column = "anumber"),
			@Result(property = "peoples", column = "anumber",
					javaType = java.lang.Integer.class,
					one = @One(select = "com.community.dao.ActivityDao.findActivityPeoplesByAnumber")
			)
	})
	List<Activity> findAllActivityList(@Param("atitle") String atitle);

	@Insert("INSERT INTO `community`.`activity` (`anumber`,`atitle`, `organizer`, `alocation`, `telephone`, `starttime`, `endtime`, `details`) VALUES ('${activity.anumber}','${activity.atitle}', '${activity.organizer}', '${activity.alocation}', '${activity.telephone}', '${starttime}', '${endtime}','${activity.details}') ")
	void createNewOneActivity(@Param("activity") Activity activity, @Param("starttime") String starttime, @Param("endtime") String endtime);

	@Update("UPDATE activity SET atitle = #{atitle},alocation = #{alocation},details=#{details} WHERE anumber=#{anumber} ")
	void changeActivityByAnumber(Activity activity);
}
