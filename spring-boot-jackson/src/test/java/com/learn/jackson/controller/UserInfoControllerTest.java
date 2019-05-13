package com.learn.jackson.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.learn.jackson.vo.User;
import com.learn.jackson.vo.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserInfoControllerTest {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	protected ObjectMapper objectMapper;

	protected ObjectWriter objectWriter;

	@Before
	public void init() {
		objectWriter = objectMapper.writer();
	}

	/**
	 * Test method for
	 * {@link com.learn.jackson.controller.UserInfoController#addUserInfo(com.learn.jackson.vo.UserInfo)}.
	 */
	@Test
//	@Ignore
	public void testAddUserInfoUserInfo() {
		UserInfo info1 = new UserInfo();
		info1.setCompanyAdress("dfff");
		info1.setCompanyName("ddd");
		info1.setId(1);
		info1.setTag("wd");
		info1.setWife("zh");
		info1.setPageNumber(1);
		info1.setPageSize(1);
		try {
			String content = objectWriter.writeValueAsString(info1);
			String message = mvc
					.perform(post("/user/info/add").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
							.content(content))
					.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for，需要支持复杂类型吗？
	 * {@link com.learn.jackson.controller.UserInfoController#addUserInfo(java.util.List)}.
	 */
	@Test
//	@Ignore
	public void testAddUserInfos() {
		List<UserInfo> infos = new ArrayList<UserInfo>();
		UserInfo info1 = new UserInfo();
		info1.setCompanyAdress("sum");
		info1.setCompanyName("ddd");
		info1.setId(1);
		info1.setTag("wd");
		info1.setWife("zh");
		info1.setPageNumber(1);
		info1.setPageSize(1);
		infos.add(info1);

		UserInfo info2 = new UserInfo();
		info2.setCompanyAdress("sum");
		info2.setCompanyName("ddd1");
		info2.setId(1);
		info2.setTag("wd1");
		info2.setWife("zh1");
		info2.setPageNumber(11);
		info2.setPageSize(11);
		infos.add(info2);
		try {
			String content = objectWriter.writeValueAsString(infos);
			String message = mvc
					.perform(post("/user/info/insert").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
							.content(content))
					.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.learn.jackson.controller.UserInfoController#listUserInfo(com.learn.jackson.vo.UserInfo)}.
	 */
	@Test
//	@Ignore
	public void testListUserInfo() {
		User user = new User();
		user.setId(1);
		user.setAdress("weifang");
		user.setName("dongzhi");
		user.setPhoneNumber("15859637258");
		user.setSex("male");
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		try {
			String content = objectWriter.writeValueAsString(user);
			String message = mvc.perform(post("/user/info/list").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(content)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
