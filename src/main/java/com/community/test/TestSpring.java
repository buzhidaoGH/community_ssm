package com.community.test;

import com.community.service.NewsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

	@Test
	public void spring1(){
		//加载spring配置文件
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//获取对象
		NewsService newsService = (NewsService)ac.getBean("newsService");
		//调用方法
		newsService.findAll();
	}
}
