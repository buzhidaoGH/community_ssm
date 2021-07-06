package com.community.service.impl;

import com.community.dao.NewsDao;
import com.community.domain.News;
import com.community.service.NewsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;

	@Override
	public List<News> findAll() {
		return newsDao.findAll();
	}

	@Override
	public void saveNews(News news) {
		newsDao.saveNews(news);
	}

	@Override
	public News findOneNewsByNnumber(String nnumber) {
		return newsDao.findOneNewsByNnumber(nnumber);
	}

	@Override
	public Integer findAllCountNews() {
		return newsDao.findAllCountNews();
	}

	@Override
	public News noticeOneNow() {
		return newsDao.noticeOne();
	}

	@Override
	public List<News> findAllNewsList(int page, int size, String ntitle) {
		PageHelper.startPage(page, size);
		return newsDao.findAllNewsList(ntitle);
	}

	@Override
	public void createNewOnenews(News news) {
		newsDao.createNewOnenews(news);
	}

	@Override
	public void changeNewsByNnumber(News news) {
		newsDao.changeNewsByNnumber(news);
	}

	@Override
	public void changeNoticeMain(String ncontent) {
		newsDao.changeNoticeMain(ncontent);
	}
}
