package com.learn.jackson.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.learn.jackson.common.core.JacksonFilterBean;
import com.learn.jackson.common.core.JacksonFilterBean.IncomeParameterIncludeFillter;
import com.learn.jackson.vo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

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
	 * {@link com.learn.jackson.controller.UserController#addUser(com.learn.jackson.vo.User)}.
	 */
	@Test
	public void testAddUser() {
		User user = new User();
		user.setId(1);
		user.setAdress("weifang");
		user.setName("dongzhi");
		user.setPhoneNumber("158**637258");
		user.setSex("male");
		try {
			String content = objectWriter.writeValueAsString(user);
			String message = mvc.perform(post("/user/add").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(content)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEditUser() {
		User user = new User();
		user.setId(1);
		user.setAdress("weifang");
		user.setName("dongzhi");
		user.setPhoneNumber("158**637258");
		user.setSex("male");
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		try {
			String content = objectWriter.writeValueAsString(user);
			String message = mvc.perform(post("/user/edit").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(content)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testListUser() {
		try {
			String message = mvc.perform(get("/user/list")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testListVip() {
		try {
			String message = mvc.perform(get("/user/vip")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	@Test
	@Ignore
	public void testAddUserMapper() throws JsonParseException, JsonMappingException, IOException {
		User user = new User();
		user.setId(1);
		user.setAdress("weifang");
		user.setName("weidong");
		user.setPhoneNumber("15859637258");
		user.setSex("male");
		System.out.println(user.toString());
		Set<String> inclueFiltFields = new HashSet<>();
		inclueFiltFields.add("name");
//		ObjectMapper objectMapper1 = new ObjectMapper();
		
		objectMapper.setFilterProvider(
				new SimpleFilterProvider().addFilter(JacksonFilterBean.INCOME_PARAMETER_INCLUDE_FILTER,
						SimpleBeanPropertyFilter.filterOutAllExcept(inclueFiltFields)));
		objectMapper.addMixIn(User.class, IncomeParameterIncludeFillter.class);
		String jsonValue = objectMapper.writeValueAsString(user);
		System.out.println(jsonValue);
		 User userT = objectMapper.readValue(jsonValue, User.class);
		 System.out.println(userT.toString());
		 
	}

}
