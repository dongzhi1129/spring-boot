package com.learn.jackson.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
import com.learn.jackson.vo.School;
import com.learn.jackson.vo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchoolControllerTest {
	

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
	 * Test method for {@link com.learn.jackson.controller.SchoolController#listSchool()}.
	 */
	@Test
	public void testListSchool() {
		
		try {
			String message = mvc.perform(get("/school/list")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.learn.jackson.controller.SchoolController#getSchool(com.learn.jackson.vo.School)}.
	 */
	@Test
	public void testGetSchool() {
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
		School school = new School();
		school.setId(12);
		school.setName("NJUPT");
		school.setUsers(users);
		try {
			String content = objectWriter.writeValueAsString(school);
			String message = mvc.perform(post("/school/get").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(content)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
