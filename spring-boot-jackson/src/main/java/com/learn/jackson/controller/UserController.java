package com.learn.jackson.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jackson.common.annotation.JacksonFilter;
import com.learn.jackson.common.annotation.JacksonFilter.JscksonFilterType;
import com.learn.jackson.common.core.ApiResult;
import com.learn.jackson.vo.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/add")
	@JacksonFilter(value = User.class, include = { "id" ,"name"}, type = JscksonFilterType.REQUEST)
	public ApiResult addUser(@RequestBody User user) {
		ApiResult apiResult = ApiResult.SUCCESS();
		List<User> users = new ArrayList<User>();
		users.add(user);
		apiResult.setRows(users);
		return apiResult;
	}
	
	@PostMapping("/edit")
	@JacksonFilter(value = User.class, exclude = {"gmtCreate","gmtModified"}, type = JscksonFilterType.REQUEST)
	public ApiResult editUser(@RequestBody User user) {
		ApiResult apiResult = ApiResult.SUCCESS();
		List<User> users = new ArrayList<User>();
		users.add(user);
		apiResult.setRows(users);
		return apiResult;
	}
	
	@GetMapping("/list")
	@JacksonFilter(value = User.class, exclude = {"gmtCreate","gmtModified"}, type = JscksonFilterType.RESPONSE)
	public ApiResult listUsers() {
		ApiResult apiResult = ApiResult.SUCCESS();
		List<User> users = new ArrayList<User>();
		
		User user1 = new User();
		user1.setId(1);
		user1.setAdress("weifang");
		user1.setName("dongzh");
		user1.setPhoneNumber("15859637**8");
		user1.setSex("male");
		users.add(user1);
		
		User user2 = new User();
		user2.setId(1);
		user2.setAdress("linyi");
		user2.setName("zhang");
		user2.setPhoneNumber("158596372**7");
		user2.setSex("female");
		users.add(user2);
		apiResult.setRows(users);
		return apiResult;
		
	}
	
	@GetMapping("/vip")
	@JacksonFilter(value = User.class, include = {"id","name"}, type = JscksonFilterType.RESPONSE)
	public ApiResult listVip() {
		ApiResult apiResult = ApiResult.SUCCESS();
		List<User> users = new ArrayList<User>();
		
		User user1 = new User();
		user1.setId(1);
		user1.setAdress("weifang");
		user1.setName("dongzhi");
		user1.setPhoneNumber("15859637**8");
		user1.setSex("male");
		users.add(user1);
		
		User user2 = new User();
		user2.setId(1);
		user2.setAdress("linyi");
		user2.setName("zhang");
		user2.setPhoneNumber("158596372**7");
		user2.setSex("female");
		users.add(user2);
		apiResult.setRows(users);
		return apiResult;
		
	}

}
