package com.learn.asyc.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.learn.asyc.core.ApiResult;
import com.learn.asyc.service.WebAsyncTaskService;
import com.learn.asyc.service.async.AsyncService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

	@Autowired
	private AsyncService asyncService;

	@GetMapping("/task")
	public ApiResult executeTask() {

		asyncService.longTimeExecuteTask();
		ApiResult apiResult = new ApiResult(0, "success");
		return apiResult;
	}

}
