package com.community.dao;

import com.community.domain.StagingTable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//中间表dao
@Repository
public interface StagingTableDao {

	//通过Anumber和Snumber查询签到状态
	@Select("select * from student_activity where anumber='${anumber}' and snumber = '${snumber}' ")
	StagingTable findSigningByAnumberAndSnumber(@Param("anumber") String anumber,@Param("snumber") String snumber);

	//通过Snumber查询签到状态
	@Select("select snumber,anumber,signing from student_activity where snumber=#{snumber}")
	List<StagingTable> findSigningBySnumber(@Param("snumber")String snumber);

	//通过snumber和anumber改变student_activity签到状态
	@Update("update student_activity set signing=1 where anumber='${anumber}' and snumber = '${snumber}'")
	void changeSigningStatus(@Param("snumber") String snumber,@Param("anumber") String anumber);

	//通过snumber和anumber对活动进行报名(student_activity)
	@Insert("INSERT INTO student_activity(anumber,snumber) VALUES ('${anumber}','${snumber}')")
	void addActivityEnrollBySnumberAndAnumber(@Param("snumber") String snumber,@Param("anumber") String anumber);

	//通过joinrole查找sumber和anumber(student_activity)
	@Select("SELECT * FROM student_community WHERE joinrole = '1';")
	List<StagingTable> findPresidentBySnumber();

	//通过snumber(cookie)和cnumber退团
	@Delete("DELETE FROM student_community WHERE snumber='${snumber}' AND cnumber='${cnumber}'")
	void quitCommunityBySnumberAndCnumber(@Param("snumber")String snumber,@Param("cnumber") String cnumber);

	//通过snumber(cookie)和cnumber加入社团 student_community
	@Insert("INSERT INTO student_community(snumber,cnumber) VALUES ('${snumber}','${cnumber}')")
	void addJoinClubSnumberAndCnumber(@Param("snumber") String snumber,@Param("cnumber") String cnumber);

	//通过snumber(cookie)和cnumber判断是否加入了该社团 student_community
	@Select("select cnumber,snumber from student_community where snumber='${snumber}' AND cnumber='${cnumber}'")
	Boolean findExistSnumberAndCnumber(@Param("snumber")String snumber,@Param("cnumber") String cnumber);

	//通过snumber和cnumber来建立社团和社长的关联(joinrole=1)
	@Insert("INSERT INTO student_community(snumber,cnumber,joinrole) VALUES ('${snumber}','${cnumber}',1)")
	void setupPresidentAndCommunity(@Param("snumber") String snumber,@Param("cnumber") String cnumber);

	//删除社团,先删除中间表,然后通过cnumber删除
	@Delete("DELETE FROM student_community WHERE cnumber='${cnumber}'")
	void deleteCommunityBycnumber(@Param("cnumber")String cnumber);
}
