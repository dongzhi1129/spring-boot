package com.learn.asyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.learn.asyc.core.ApiResult;

@Service
public class StompServiceImpl {

	@Autowired
	// 使用SimpMessagingTemplate 向浏览器发送消息
	private SimpMessagingTemplate template;

	public void sendMessage() throws Exception {
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			template.convertAndSend("/topic/getResponse", new ApiResult(0, "" + i));
			System.out.println("----------------------yangyibo" + i);
		}
	}

}
