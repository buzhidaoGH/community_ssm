package com.community.test;

import com.community.dao.*;
import com.community.domain.News;
import com.community.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
	@Test
	public void run1() throws IOException {
		//加载配置文件
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		//创建SqlSessionFactory对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		//使用工厂,创建SqlSession对象
		SqlSession session = factory.openSession();
		//获取到代理对象
		NewsDao dao = session.getMapper(NewsDao.class);
		List<News> all = dao.findAll();
		for (News news : all) {
			System.out.println(news);
		}

		//关闭资源
		session.close();
		is.close();
	}

	@Test
	public void run2() throws IOException {
		//加载配置文件
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		//创建SqlSessionFactory对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		//使用工厂,创建SqlSession对象
		SqlSession session = factory.openSession();
		//获取到代理对象
		NewsDao dao = session.getMapper(NewsDao.class);

		News news = new News();
		news.setNauthor("测试");
		news.setNcontent("内容啊");
		news.setNnumber("174212132");

		dao.saveNews(news);

		//提交事务
		session.commit();

		//关闭资源
		session.close();
		is.close();
	}

	@Test
	public void insetStudent() throws IOException {
		//加载配置文件
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		//创建SqlSessionFactory对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		//使用工厂,创建SqlSession对象
		SqlSession session = factory.openSession();
		//获取到代理对象
		StudentDao dao = session.getMapper(StudentDao.class);
		Student student = new Student();
		for (int i = 29; i < 63; i++) {
			student.setSname("测试" + i);
			student.setSnumber("17413020" + i);
			student.setSpassword("123456");
			student.setSgender(((int) (10 * Math.random())) % 2);
			student.setCollegeClass("随机住址" + i);
			student.setTelephone(((int) (100000 * Math.random())) + "");
			try {//处理异常
				dao.insertStudent(student);
				//提交事务
				session.commit();
			} catch (Exception e) {//跳过异常
				continue;
			}
		}
		//关闭资源
		session.close();
		is.close();
	}

	@Test
	public void insetStudentOne() throws IOException {
		//加载配置文件
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		//创建SqlSessionFactory对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		//使用工厂,创建SqlSession对象
		SqlSession session = factory.openSession();
		//获取到代理对象
		StudentDao dao = session.getMapper(StudentDao.class);
		Student student = new Student();
		student.setSname("测试");
		student.setSnumber("17413020");
		student.setSpassword("123456");
		student.setSgender(((int) (10 * Math.random())) % 2);
		student.setCollegeClass("随机住址");
		student.setTelephone(((int) (100000 * Math.random())) + "");
		dao.insertStudent(student);
		//提交事务
		session.commit();

		//关闭资源
		session.close();
		is.close();
	}
}
