package com.learn.asyc.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.learn.asyc.service.WebAsyncTaskService;

@Service
public class WebAsyncTaskServiceImpl implements WebAsyncTaskService {

	@Override
	public void exhaustTimeTask() throws Exception {
		// TODO Auto-generated method stub
		TimeUnit.SECONDS.sleep(5);
		throw new Exception("failed");
	}

}
