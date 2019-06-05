package com.learn.asyc.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.learn.asyc.core.ApiResult;
import com.learn.asyc.service.WebAsyncTaskService;
import com.learn.asyc.service.async.AsyncService;
import com.learn.asyc.service.impl.StompServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/async")
@Slf4j
public class StompController {

	@Autowired
	private StompServiceImpl stompServiceImpl;

	@MessageMapping("/stomp") 
	@SendTo("/topic/getResponse")
	public ApiResult say(String message) throws Exception {
		return new ApiResult(0, message);
	}

	@GetMapping("/stomp/start")
	public ApiResult start() {

		try {
			stompServiceImpl.sendMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ApiResult(0, "success");
	}

}
