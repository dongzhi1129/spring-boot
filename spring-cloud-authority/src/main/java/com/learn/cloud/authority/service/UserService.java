package com.learn.cloud.authority.service;

import java.util.List;

import com.learn.cloud.authority.common.core.BusinessException;
import com.learn.cloud.authority.domain.Resource;
import com.learn.cloud.authority.domain.Role;
import com.learn.cloud.authority.domain.User;

public interface UserService {

	void addUser(User user) throws BusinessException;

	User selectUserByLoginName(String loginName);

	List<Role> listUserRoles(String loginName);

	List<Resource> listUserResource(String loginName);

}
