package com.community.service.impl;

import com.community.dao.MessageDao;
import com.community.domain.Message;
import com.community.service.MessageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public Integer findAllCountMessage() {
		return messageDao.findAllCountMessage();
	}

	@Override
	public List<Message> findAllMessageList(int page, int size, String peopleid,String status) {
		PageHelper.startPage(page,size);
		return messageDao.findAllMessageList(peopleid,status);
	}

	@Override
	public void checkMessageByPeopleId(Integer meid) {
		messageDao.checkMessageByPeopleId(meid);
	}

	@Override
	public void deleteMessageByPeopleId(Integer meid) {
		messageDao.deleteMessageByPeopleId(meid);
	}
}
