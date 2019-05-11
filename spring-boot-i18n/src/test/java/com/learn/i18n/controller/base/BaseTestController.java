package com.learn.i18n.controller.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public abstract class BaseTestController {

	protected TestContextManager testContextManager;

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	protected ObjectWriter objectWriter;

	@Before
	public void init() throws Exception {
		testContextManager = new TestContextManager(getClass());
		testContextManager.prepareTestInstance(this);
		objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

	}

}
