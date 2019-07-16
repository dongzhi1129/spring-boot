package com.learn.cloud.authority.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.learn.cloud.authority.common.constant.AuthorityConstants;
import com.learn.cloud.authority.common.core.ApiResult;
import com.learn.cloud.authority.common.util.DateUtils;
import com.learn.cloud.authority.domain.User;
import com.learn.cloud.authority.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/authority")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/redirect")
	public ApiResult redirectForLogin() {
		ApiResult apiResult = ApiResult.success();
		apiResult.setMessage("please login.");
		return apiResult;
	}

	@GetMapping("/unAuthor")
	public ApiResult unAuthorization() {
		ApiResult apiResult = ApiResult.success();
		apiResult.setMessage("do not hava this permission");
		return apiResult;
	}

	@PostMapping("/login")
	public ApiResult login(@RequestParam(required = true, name = "loginName") String loginName,
			@RequestParam(required = true, name = "password") String password) {
		UsernamePasswordToken passwordToken = new UsernamePasswordToken(loginName, password);
		try {
			SecurityUtils.getSubject().login(passwordToken);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			log.error("login failed.", e);
			ApiResult apiResult = ApiResult.failed();
			return apiResult.setMessage(e.getMessage());
		}
		/*
		 * ServletRequestAttributes requestAttributes =
		 * (ServletRequestAttributes) RequestContextHolder
		 * .getRequestAttributes(); HttpSession session =
		 * requestAttributes.getRequest().getSession();
		 * session.setAttribute("test", "123");
		 */
		return ApiResult.success();
	}

	@PostMapping("/logout")
	public ApiResult logout() {
		try {
			SecurityUtils.getSubject().logout();
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			log.error("login failed.", e);
			ApiResult apiResult = ApiResult.failed();
			return apiResult.setMessage(e.getMessage());
		}
		/*
		 * ServletRequestAttributes requestAttributes =
		 * (ServletRequestAttributes) RequestContextHolder
		 * .getRequestAttributes(); HttpSession session =
		 * requestAttributes.getRequest().getSession();
		 * session.setAttribute("test", "123");
		 */
		return ApiResult.success();
	}

	@GetMapping("/check/permission")
	public ApiResult checkPermission(@RequestParam(required = true, name = "permission") String permission) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		try {
			SecurityUtils.getSubject().checkPermission(permission);
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			ApiResult apiResult = ApiResult.failed();
			apiResult.setMessage("权限验证不通过");
			return apiResult;
		}
		ApiResult apiResult = ApiResult.success();
		return apiResult;
	}

	@PostMapping("/register")
	public ApiResult register(@RequestBody User user) {
		if (StringUtils.isBlank(user.getSalt())) {
			user.setSalt(AuthorityConstants.DEFAULT_CRYPTO_SALT);
		}
		Md5Hash md5Hash = new Md5Hash(user.getPassword(), user.getSalt(), AuthorityConstants.DEFAULT_CRYPTO_ITERATIONS);
		user.setGmtCreate(DateUtils.getCurrentDate());
		user.setPassword(md5Hash.toHex());
		userService.addUser(user);
		return ApiResult.success();

	}

}
