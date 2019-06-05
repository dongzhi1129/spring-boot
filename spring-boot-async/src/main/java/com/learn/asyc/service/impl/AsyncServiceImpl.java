package com.learn.asyc.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.learn.asyc.service.async.AsyncService;

@Service
public class AsyncServiceImpl implements AsyncService {

	@Override
	@Async
	public void longTimeExecuteTask() {
		// TODO Auto-generated method stub

		try {
			System.out.println(String.format("Thread-{%s} is be working", Thread.currentThread().getId()));
			TimeUnit.SECONDS.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(String.format("Thread-{%s} is be interrupt", Thread.currentThread().getId()));
		}

	}

}
