package com.community.service;

import com.community.domain.Message;

import java.util.List;

public interface MessageService {
	Integer findAllCountMessage();

	List<Message> findAllMessageList(int page, int size, String peopleid,String status);

	void checkMessageByPeopleId(Integer meid);

	void deleteMessageByPeopleId(Integer meid);
}
