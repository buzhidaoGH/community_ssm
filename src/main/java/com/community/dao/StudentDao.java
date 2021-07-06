package com.community.dao;

import com.community.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//学生用户持久层dao
@Repository
public interface StudentDao {

	//通过社团cnumber查询社长名字
	@Select("SELECT sname,snumber,telephone FROM student WHERE snumber IN (SELECT snumber FROM student_community WHERE cnumber = #{cnumber} AND joinrole=1)")
	Student findPresidentByCnumber(@Param("cnumber")String cnumber);

	//通过社团cnmuber查询社长的所有信息(局部)
	@Select("SELECT sname,snumber,telephone FROM student WHERE snumber IN (SELECT snumber FROM student_community WHERE cnumber = #{cnumber} AND joinrole=1)")
	List<Student> findPresidentsByCnumber(@Param("cnumber")String cnumber);

	//通过社团cnumber查询该社团的所有人信息(包括社长和社员)
	@Select("SELECT sname,snumber,telephone,sgender FROM student WHERE snumber IN (SELECT snumber FROM student_community WHERE cnumber = #{cnumber})")
	List<Student> findPeopleListByCnumber(@Param("cnumber")String cnumber);

	//通过活动anumber查询该活动的所有参与人
	@Select("SELECT sname,snumber,telephone,sgender FROM student WHERE snumber IN (SELECT snumber FROM student_activity WHERE anumber = #{anumber})")
	List<Student> findPeopleListByAnumber(@Param("anumber")String anumber);


	//账户密码验证
	@Select("select snumber from student where snumber='${snumber}' and spassword='${spassword}'")
	List<Student> loginValidation(@Param("snumber") String snumber, @Param("spassword") String spassword);

	//通过学生表的snumber查询个人信息(包括社团和活动)
	@Select("select * from student where snumber = #{snumber}")
	@Results({
			@Result(id=true,property = "sid",column = "sid"),
			@Result(property = "snumber",column = "snumber"),
			@Result(property = "spassword",column = "spassword"),
			@Result(property = "sname",column = "sname"),
			@Result(property = "sgender",column = "sgender"),
			@Result(property = "telephone",column = "telephone"),
			@Result(property = "collegeClass",column = "collegeClass"),
			@Result(property = "register",column = "register"),
			@Result(property = "srole",column = "srole"),
			@Result(property = "imgpath",column = "imgpath"),
			@Result(property = "communities",column = "snumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.CommunityDao.findBySnumber")),
			@Result(property = "activities",column = "snumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.ActivityDao.findBySnumber")),
			@Result(property = "messages",column = "snumber",
					javaType = java.util.List.class,
					many = @Many(select = "com.community.dao.MessageDao.findAllMessageBySnumber"))
	})
	Student findPersonalCenterBySnumber(@Param("snumber") String snumber);

	//更新用户的头像imgpath,通过snumber
	@Update("update student set imgpath='${snumber}.png' where snumber=#{snumber}")
	void changeImgpathBySnumber(@Param("snumber") String snumber);

	//更新个性签名,通过snumber
	@Update("update student set signature='${signature}' where snumber='${snumber}'")
	void changeSignatureBySnumber(@Param("snumber")String community,@Param("signature")String signature);

	//更改密码,通过snumber和npassword
	@Update("update student set spassword='${npassword}' where snumber='${snumber}'")
	void changePasswordBySnumberAndSpassword(@Param("snumber") String snumber,@Param("npassword") String npassword);

	//查询所有学生数量
	@Select("SELECT COUNT(*) FROM student")
	Integer findAllCountStudent();

	//模糊分页查询:模糊(sname和snumber)
	@Select("SELECT * FROM student WHERE sname LIKE CONCAT('%','${sname}','%') AND snumber LIKE CONCAT('%','${snumber}','%')")
	List<Student> findAllStudentList(@Param("snumber") String snumber,@Param("sname") String sname);

	//通过peopleid(snumber)查找sname
	@Select("select sname from student where snumber = #{peopleid}")
	String findSnameBySnumber(@Param("peopleid") String peopleid);

	//插入学生数据
	@Insert("INSERT INTO `community`.`student` (`snumber`, `spassword`, `sname`, `sgender`, `telephone`, `collegeClass`) VALUES ('${snumber}', '${spassword}', '${sname}', '${sgender}', '${telephone}', '${collegeClass}')")
	void insertStudent(Student student);
}
