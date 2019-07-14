package com.learn.cloud.authority.common.core;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.learn.cloud.authority.common.constant.AuthorityConstants;
import com.learn.cloud.authority.common.constant.UserStatusEnum;
import com.learn.cloud.authority.domain.Resource;
import com.learn.cloud.authority.domain.Role;
import com.learn.cloud.authority.domain.User;
import com.learn.cloud.authority.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String loginName = (String) principals.getPrimaryPrincipal();
		List<Role> roles = userService.listUserRoles(loginName);
		Set<String> roleSet = null;
		if (CollectionUtils.isNotEmpty(roles)) {
			roleSet = roles.parallelStream().map(elem -> {
				return elem.getName();
			}).collect(Collectors.toSet());
		} else {
			roleSet = Sets.newHashSet();
		}
		List<Resource> resources = userService.listUserResource(loginName);
		Set<String> permissions = null;
		if (CollectionUtils.isNotEmpty(resources)) {
			permissions = resources.parallelStream().map(elem -> {
				return elem.getUrl();
			}).collect(Collectors.toSet());
		} else {
			permissions = Sets.newHashSet();
		}

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roleSet);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String loginName = (String) token.getPrincipal();
		User user = userService.selectUserByLoginName(loginName);
		if (user == null) {
			log.error("Can not find user[{}]", loginName);
			throw new UnknownAccountException(String.format("[%s] is not exist.", loginName));
		}
		if (UserStatusEnum.valueOf(user.getStatus()) == UserStatusEnum.USER_STATUS_LOCKED) {
			log.error("user[{}] is locked.", loginName);
			throw new UnknownAccountException(String.format("[%s] is locked,please contact to adminster.", loginName));
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(),
				user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		return simpleAuthenticationInfo;
	}

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
		shaCredentialsMatcher.setHashAlgorithmName(AuthorityConstants.DEFAULT_ALGRITHOM_MD5HASH);
		shaCredentialsMatcher.setHashIterations(AuthorityConstants.DEFAULT_CRYPTO_ITERATIONS);
		super.setCredentialsMatcher(shaCredentialsMatcher);
	}

}
