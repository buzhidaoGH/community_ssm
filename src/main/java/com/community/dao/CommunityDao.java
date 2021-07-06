package com.community.dao;

import com.community.domain.Community;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//社团dao层
@Repository
public interface CommunityDao {

	//查询所有社团,简单版
	@Select("SELECT cnumber,cname,imgpath FROM community")
	List<Community> findAllCommunityData();

	//按照社团cnumber查询社团简介和海报一张
	@Select("SELECT cnumber,cname,imgpath,creationtime,introduction FROM community")
	List<Community> findAllCommunity();

	//查询第一个社团,和社团内的所有成员包括社长
	@Select("SELECT cnumber,cname,imgpath,creationtime,introduction FROM community limit 1")
	@Results({
			@Result(id = true, property = "cid", column = "cid"),
			@Result(property = "cnumber", column = "cnumber"),
			@Result(property = "peopleList", column = "cnumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.StudentDao.findPeopleListByCnumber")
			)
	})
	Community findFirstCommunityData();

	//查询一个社团通过cnumber
	@Select("SELECT cnumber,cname,imgpath,creationtime,introduction FROM community where cnumber = #{cnumber}")
	Community findOneCommunityData(@Param("cnumber") String cnumber);

	//查询所有社团,通过snumber(学生number),再通过社团cnumber查社长
	@Select("SELECT cnumber,cname FROM community WHERE cnumber IN (SELECT cnumber FROM student_community WHERE snumber=#{snumber});")
	@Results({
			@Result(id = true, property = "cid", column = "cid"),
			@Result(property = "cnumber", column = "cnumber"),
			@Result(property = "cname", column = "cname"),
			@Result(property = "creationtime", column = "creationtime"),
			@Result(property = "introduction", column = "introduction"),
			@Result(property = "imgpath", column = "imgpath"),
			@Result(property = "students", column = "cnumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.StudentDao.findPresidentsByCnumber")
			),
			@Result(property = "peopleList", column = "cnumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.StudentDao.findPeopleListByCnumber")
			)
	}
	)
	List<Community> findBySnumber(@Param("snumber") String snumber);

	//查询一个社团通过cnumber(详细信息)
	@Select("SELECT cnumber,cname,imgpath,creationtime,introduction,notice FROM community where cnumber = #{cnumber}")
	@Results({
			@Result(id = true, property = "cid", column = "cid"),
			@Result(property = "cnumber", column = "cnumber"),
			@Result(property = "students", column = "cnumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.StudentDao.findPresidentsByCnumber")
			),
			@Result(property = "peoples", column = "cnumber",
					javaType = java.lang.Integer.class,
					one = @One(select = "com.community.dao.CommunityDao.findCommunityPeoplesByCnumber")
			),
			@Result(property = "peopleList", column = "cnumber",
			javaType = java.util.List.class,
			many = @Many(select = "com.community.dao.StudentDao.findPeopleListByCnumber")
	)
	})
	Community findOneCommunityByCnumber(@Param("cnumber") String cnumber);

	//查询一个社团的人数,by cnumber
	@Select("SELECT COUNT(*) FROM student_community WHERE cnumber=#{cnumber}")
	Integer findCommunityPeoplesByCnumber(@Param("cnumber") String cnumber);

	//查询总社团数量
	@Select("SELECT COUNT(*) FROM community")
	Integer findAllCountCommunity();

	//模糊查询,根据社团名称
	@Select("SELECT * FROM community WHERE cname LIKE CONCAT('%',#{cname},'%')")
	@Results({
			@Result(id = true, property = "cid", column = "cid"),
			@Result(property = "cnumber", column = "cnumber"),
			@Result(property = "students", column = "cnumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.StudentDao.findPresidentsByCnumber")
			),
			@Result(property = "peoples", column = "cnumber",
					javaType = java.lang.Integer.class,
					one = @One(select = "com.community.dao.CommunityDao.findCommunityPeoplesByCnumber")
			)
	})
	List<Community> findAllCommunityList(@Param("cname") String cname);

	//通过cnumber来更改community中的notice和introduction
	@Update("UPDATE community SET notice = '${notice}',introduction = '${introduction}' WHERE cnumber='${cnumber}'")
	void changeCommunityNoticeIntroduction(@Param("cnumber")String cnumber,@Param("notice") String notice,@Param("introduction") String introduction);

	//通过cnumber来更改imgpath路径
	@Update("UPDATE community SET imgpath = '${cnumber}.png'WHERE cnumber='${cnumber}'")
	void changeCommunityImage(@Param("cnumber")String cnumber);

	//通过community对象来创建社团
	@Insert("INSERT INTO `community`.`community` (`cnumber`, `cname`, `introduction`, `notice`, `imgpath`) VALUES ('${community.cnumber}', '${community.cname}', '${community.introduction}', '${community.notice}', '${community.cnumber}.png') ")
	void createCommunity(@Param("community")Community community);

	@Delete("DELETE FROM community WHERE cnumber=#{cnumber} ")
	void deleteCommunityBycnumber(String cnumber);
}
