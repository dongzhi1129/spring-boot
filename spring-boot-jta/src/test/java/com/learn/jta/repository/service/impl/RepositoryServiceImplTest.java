package com.learn.jta.repository.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.jta.repository.service.RepositoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryServiceImplTest {
	
	@Autowired
	private RepositoryService repositoryService;

	@Test
	public void testUpdateRepository() {
		try {
			repositoryService.updateRepository(1, 0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
