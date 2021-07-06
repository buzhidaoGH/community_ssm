package com.community.service;

import com.community.domain.News;

import java.util.List;

public interface NewsService {

	//查询所有新闻
	public List<News> findAll();

	//新建一条新闻
	public void saveNews(News news);

	//查找一条新闻详情
	News findOneNewsByNnumber(String nnumber);

	//聚合所有新闻数据(去除公告)
	Integer findAllCountNews();

	//查询轮播一条公告
	News noticeOneNow();

	//查询所有新闻信息,支持分页和模糊查询
	List<News> findAllNewsList(int page, int size, String ntitle);

	//创建新的新闻
	void createNewOnenews(News news);

	//更改新闻信息
	void changeNewsByNnumber(News news);

	//更改轮播公告
	void changeNoticeMain(String ncontent);
}
