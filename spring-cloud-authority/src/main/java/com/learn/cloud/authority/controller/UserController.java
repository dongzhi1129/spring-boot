package com.learn.cloud.authority.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
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
@RequestMapping("")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

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
		ServletRequestAttributes requestAttributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = requestAttributes.getRequest().getSession();
		session.setAttribute("test", "123");
		return ApiResult.success();
	}
	
	@GetMapping("/info")
	public ApiResult getInfo() {
		ServletRequestAttributes requestAttributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		ApiResult apiResult = ApiResult.success();
		HttpSession session = requestAttributes.getRequest().getSession();
		log.debug(session.getId());
		log.debug((String) session.getAttribute("test"));
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
