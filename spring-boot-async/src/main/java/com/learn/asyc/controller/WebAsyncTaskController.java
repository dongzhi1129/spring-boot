package com.learn.asyc.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.learn.asyc.core.ApiResult;
import com.learn.asyc.service.WebAsyncTaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/async")
@Slf4j
public class WebAsyncTaskController {

	@Autowired
	private WebAsyncTaskService asyncTaskService;

	@GetMapping
	public WebAsyncTask<ApiResult> executeTask() {
		log.info("main方法开始执行，thread->[{}]", Thread.currentThread().getName());
		Callable<ApiResult> task = () -> {
			log.info("async方法开始执行，thread->[{}]", Thread.currentThread().getName());
			asyncTaskService.exhaustTimeTask();
			ApiResult apiResult = new ApiResult();
			apiResult.setMessage("success");
			apiResult.setStatus(0);
			return apiResult;
		};
		WebAsyncTask<ApiResult> webAsyncTask = new WebAsyncTask<ApiResult>(1000 * 10l, task);
		webAsyncTask.onCompletion(() -> log.info("task finished."));

		webAsyncTask.onError(() -> {
			System.out.println("发生异常");
			ApiResult apiResult = new ApiResult();
			apiResult.setMessage("failed");
			apiResult.setStatus(-1);
			return apiResult;
		});

		webAsyncTask.onTimeout(() -> {
			ApiResult apiResult = new ApiResult();
			apiResult.setMessage("failure.");
			apiResult.setStatus(-1);
			return apiResult;
		});
		return webAsyncTask;
	}

}
