package com.learn.cloud.authority.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.cloud.authority.common.constant.SexTypeEnum;
import com.learn.cloud.authority.common.constant.UserStatusEnum;
import com.learn.cloud.authority.common.core.BusinessException;
import com.learn.cloud.authority.common.util.DateUtils;
import com.learn.cloud.authority.domain.User;
import com.learn.cloud.authority.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(value = false)
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void testAddUser() {
		User user = new User();
		user.setLoginName("dongzhi.wang");
		user.setName("dongzhi.wang");
		user.setPassword("123456");
		user.setSex((byte) 0);
		user.setUserType((byte) 0);
		user.setStatus((byte) 0);
		user.setOrganizationId(1);
		user.setGmtCreate(DateUtils.getCurrentDate());
		try {
			userService.addUser(user);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		User user = new User();
		user.setLoginName("dongzhi.wang");
		user.setPassword("123456");
		user.setOrganizationId(1);
		user.setPhone("1836456897");
		user.setAge((byte) 18);
		user.setSex(SexTypeEnum.SEX_TYPE_MALE.getType());
		user.setStatus(UserStatusEnum.USER_STATUS_UNLOCK.getStatus());
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(user));
	}

}
