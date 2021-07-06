package com.community.service.impl;

import com.community.dao.StudentDao;
import com.community.domain.Community;
import com.community.domain.Student;
import com.community.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	@Override
	public Student findPresidentByCnumber(String cnumber) {
		return studentDao.findPresidentByCnumber(cnumber);
	}

	@Override
	public Integer findAllCountStudent() {
		return studentDao.findAllCountStudent();
	}

	@Override
	public List<Student> findAllStudentList(int page, int size, String snumber, String sname) {
		PageHelper.startPage(page,size);
		return studentDao.findAllStudentList(snumber,sname);
	}

	@Override
	public void studentRegister(Student student) {
		studentDao.insertStudent(student);
	}
}
