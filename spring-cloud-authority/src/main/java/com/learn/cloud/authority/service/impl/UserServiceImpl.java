package com.learn.cloud.authority.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.learn.cloud.authority.common.core.BusinessException;
import com.learn.cloud.authority.domain.ResourceExample;
import com.learn.cloud.authority.domain.Role;
import com.learn.cloud.authority.domain.RoleExample;
import com.learn.cloud.authority.domain.RoleResource;
import com.learn.cloud.authority.domain.RoleResourceExample;
import com.learn.cloud.authority.domain.User;
import com.learn.cloud.authority.domain.UserExample;
import com.learn.cloud.authority.domain.UserRole;
import com.learn.cloud.authority.domain.UserRoleExample;
import com.learn.cloud.authority.mapper.ResourceMapper;
import com.learn.cloud.authority.mapper.RoleMapper;
import com.learn.cloud.authority.mapper.UserMapper;
import com.learn.cloud.authority.mapper.UserRoleMapper;
import com.learn.cloud.authority.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private UserRoleMapper userRoleMapper;

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private ResourceMapper resourceMapper;

	@Resource
	private com.learn.cloud.authority.mapper.RoleResourceMapper roleResourceMapper;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		try {
			int affectRows = userMapper.insertSelective(user);
			if (affectRows <= 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("add user failed", e);
			throw new BusinessException("Add User failed.", e);
		}

	}

	@Override
	public User selectUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andLoginNameEqualTo(loginName);

		List<User> users = userMapper.selectByExample(userExample);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<Role> listUserRoles(String loginName) {
		// TODO Auto-generated method stub
		User user = selectUserByLoginName(loginName);
		if (user == null) {
			return Lists.newArrayList();
		}
		Long userId = user.getId();
		UserRoleExample userRoleExample = new UserRoleExample();
		UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
		if (CollectionUtils.isEmpty(userRoles)) {
			return Lists.newArrayList();
		}
		RoleExample roleExample = new RoleExample();
		RoleExample.Criteria criteria2 = roleExample.createCriteria();
		criteria2.andIdIn(userRoles.stream().map(elem -> {
			return elem.getId();
		}).collect(Collectors.toList()));
		return roleMapper.selectByExample(roleExample);
	}

	@Override
	public List<com.learn.cloud.authority.domain.Resource> listUserResource(String loginName) {
		// TODO Auto-generated method stub
		List<Role> roles = listUserRoles(loginName);
		if (CollectionUtils.isEmpty(roles)) {
			return Lists.newArrayList();
		}
		List<Long> roleIds = roles.stream().map(elem -> {
			return elem.getId();
		}).collect(Collectors.toList());

		RoleResourceExample roleResourceExample = new RoleResourceExample();
		RoleResourceExample.Criteria criteria = roleResourceExample.createCriteria();
		criteria.andRoleIdIn(roleIds);
		List<RoleResource> roleResourcese = roleResourceMapper.selectByExample(roleResourceExample);
		if (CollectionUtils.isEmpty(roleResourcese)) {
			return Lists.newArrayList();
		}
		Set<Long> resourceIds = roleResourcese.parallelStream().map(elem -> {
			return elem.getResourceId();
		}).collect(Collectors.toSet());
		ResourceExample resourceExample = new ResourceExample();
		ResourceExample.Criteria criteria2 = resourceExample.createCriteria();
		;
		criteria2.andIdIn(resourceIds.parallelStream().map(elem -> {
			return elem;
		}).collect(Collectors.toList()));

		return resourceMapper.selectByExample(resourceExample);
	}

}
