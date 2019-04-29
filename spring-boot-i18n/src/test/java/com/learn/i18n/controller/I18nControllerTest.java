package com.learn.i18n.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.learn.i18n.component.I18nComponent;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class I18nControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHelloWord() {
		try {
			mockMvc.perform(get("/i18n/hello").header(I18nComponent.HTTP_ACCEPT_LANGUAGE, Locale.CHINESE)).andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}

}
