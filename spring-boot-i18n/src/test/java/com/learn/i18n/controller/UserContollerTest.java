package com.learn.i18n.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.i18n.component.I18nComponent;
import com.learn.i18n.controller.base.BaseTestController;
import com.learn.i18n.vo.UserVo;

import junitparams.Parameters;

public class UserContollerTest extends BaseTestController {

	@Test
	@Ignore
	@Parameters(source = TestAddUserProvider.class)
	public void testAddUser(UserVo user, String locale) {
		try {
			String content = objectWriter.writeValueAsString(user);
			mockMvc.perform(post("/user/add").header(I18nComponent.HTTP_ACCEPT_LANGUAGE, locale)
					.contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andDo(print())
					.andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	@Parameters(value = { "0,en-us", "1,zh" })
	public void testListUser(String id, String locale) {
		try {
			mockMvc.perform(get("/user/list").header(I18nComponent.HTTP_ACCEPT_LANGUAGE, locale).param("id",
					new String[] { id })).andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	@Parameters(source = TestEditUserrProvider.class)
	public void testEditUser(UserVo user, String locale) {
		try {
			String content = objectWriter.writeValueAsString(user);
			mockMvc.perform(post("/user/edit").header(I18nComponent.HTTP_ACCEPT_LANGUAGE, locale)
					.contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andDo(print())
					.andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testI18nPlaceHold() {
		System.out.println(i18n.getLoacleMessageWithPlaceHolder("module.error.object.must.not.be.empty","user","name"));
	}

	public static class TestAddUserProvider {

		public static Object[] provideTestCase1() {
			UserVo user = new UserVo();
			user.setId(1l);
			user.setName("");
			user.setPhoneNumber("18362978***");
			user.setAddress("china");
			user.setSex("male");
			Object[] case1 = { user, "en-us" };
			Object[] case2 = { user, "zh" };
			return new Object[] { case1, case2 };

		}
	}

	public static class TestEditUserrProvider {

		public static Object[] provideTestCase1() {
			UserVo user = new UserVo();
			user.setId(1l);
			user.setName("dongzhi");
			user.setPhoneNumber("18362978***");
			user.setAddress("china");
			user.setSex("male");
			Object[] case1 = { user, "en-us" };
			Object[] case2 = { user, "zh" };
			return new Object[] { case1, case2 };

		}
	}

}
