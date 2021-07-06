package com.community.service;

import com.community.domain.Community;
import com.community.domain.Student;

import java.util.List;

public interface StudentService {
	Student findPresidentByCnumber(String cnumber);

	Integer findAllCountStudent();

	List<Student> findAllStudentList(int page, int size, String snumber, String sname);

	void studentRegister(Student student);
}
