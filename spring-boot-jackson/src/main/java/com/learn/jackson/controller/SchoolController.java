package com.learn.jackson.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jackson.common.annotation.JacksonFilter;
import com.learn.jackson.common.annotation.JacksonFilter.JscksonFilterType;
import com.learn.jackson.common.core.ApiResult;
import com.learn.jackson.vo.School;
import com.learn.jackson.vo.User;

@RestController
@RequestMapping("/school")
public class SchoolController {

	@GetMapping("/list")
	@JacksonFilter(value = School.class, exclude = { "users" }, type = JscksonFilterType.RESPONSE)
	public ApiResult listSchool() {
		ApiResult apiResult = ApiResult.SUCCESS();

		List<User> users = new ArrayList<User>();

		User user1 = new User();
		user1.setId(1);
		user1.setAdress("tongfang");
		user1.setName("dongzhi.wang");
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
		School school = new School();
		school.setId(12);
		school.setName("NJUPT");
		school.setUsers(users);

		List<School> schools = new ArrayList<School>();
		schools.add(school);

		apiResult.setRows(schools);
		return apiResult;

	}

	@PostMapping("/get")
	@JacksonFilter(value = School.class, exclude = { "users","id" }, type = JscksonFilterType.REQUEST)
	public ApiResult getSchool(@RequestBody School school) {
		ApiResult apiResult = ApiResult.SUCCESS();

		/*
		 * List<User> users = new ArrayList<User>();
		 * 
		 * User user1 = new User(); user1.setId(1); user1.setAdress("weifang");
		 * user1.setName("dongzhi"); user1.setPhoneNumber("15859637**8");
		 * user1.setSex("male"); users.add(user1);
		 * 
		 * User user2 = new User(); user2.setId(1); user2.setAdress("linyi");
		 * user2.setName("zhang"); user2.setPhoneNumber("158596372**7");
		 * user2.setSex("female"); users.add(user2); School school = new
		 * School(); school.setId(12); school.setName("NJUPT");
		 * school.setUsers(users);
		 */

		List<School> schools = new ArrayList<School>();
		schools.add(school);

		apiResult.setRows(schools);
		return apiResult;

	}

}
