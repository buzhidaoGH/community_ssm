package com.community.dao;

import com.community.domain.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * message表的Dao层
 */
@Repository
public interface MessageDao {
	//通过snumber和mecontext添加留言记录
	@Insert("INSERT INTO message(peopleid,mecontext) VALUES ('${snumber}','${mecontext}');")
	void addMessage(@Param("snumber") String snumber, @Param("mecontext") String mecontext);

	//通过snumber查询这个id的所有留言
	@Select("select * from message where peopleid=#{snumber}")
	List<Message> findAllMessageBySnumber(@Param("snumber") String snumber);

	//查询所有留言记录的总数(count)
	@Select("SELECT COUNT(*) FROM message")
	Integer findAllCountMessage();

	//查询所有留言记录,并且查出发言人
	@Select("SELECT * FROM message WHERE peopleid LIKE CONCAT('%','${peopleid}','%') AND message.status LIKE CONCAT('%','${status}','%')")
	@Results({
			@Result(id = true, property = "peopleid", column = "peopleid"),
			@Result(property = "sname", column = "peopleid",
					javaType = java.lang.String.class,
					one = @One(select = "com.community.dao.StudentDao.findSnameBySnumber")
			)
	})
	List<Message> findAllMessageList(@Param("peopleid") String peopleid,@Param("status") String status);

	//查阅留言(更改为已查阅_status=1)
	@Update("UPDATE message SET message.status = '1' WHERE meid = #{meid} ")
	void checkMessageByPeopleId(@Param("meid")Integer meid);

	//删除留言
	@Delete("DELETE FROM message WHERE meid= #{meid} ")
	void deleteMessageByPeopleId(@Param("meid")Integer meid);
}
