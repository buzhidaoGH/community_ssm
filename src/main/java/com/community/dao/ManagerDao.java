package com.community.dao;

import com.community.domain.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员Dao层
 */
@Repository
public interface ManagerDao {

	//查询所有管理员信息
	@Select("SELECT mname,telephone FROM manager;")
	List<Manager> managerSimple();

	//登录标记是否正确,是否正确的mnumber和mpassword
	@Select("select * from manager where mnumber='${mnumber}' and mpassword='${mpassword}' ")
	List<Manager> backLoginValidation(@Param("mnumber") String mnumber,@Param("mpassword") String mpassword);
}
