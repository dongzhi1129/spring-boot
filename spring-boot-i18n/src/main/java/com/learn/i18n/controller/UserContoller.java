package com.learn.i18n.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.i18n.common.core.ApiResult;
import com.learn.i18n.common.core.BusinessException;
import com.learn.i18n.common.core.I18nResourceCode;
import com.learn.i18n.vo.UserVo;

@RestController
@RequestMapping("/user")
@Validated
public class UserContoller {

	@PostMapping("/add")
	public ApiResult addUser(@RequestBody @Validated UserVo user) {
		return ApiResult.success();
	}

	@GetMapping("/list")
	public ApiResult listUser(
			@RequestParam(required = true, name = "id") @Range(min = 1, max = Long.MAX_VALUE, message = I18nResourceCode.USER_ERROR_ID_IS_NOT_INT_RANGE) Long id) {
		ApiResult apiResult = ApiResult.success();
		UserVo user = new UserVo();
		user.setId(1l);
		user.setName("dongzhi");
		user.setPhoneNumber("18362978***");
		user.setAddress("china");
		user.setSex("male");
		List<UserVo> users = new ArrayList<UserVo>();
		return apiResult.setRows(users);
	}

	@PostMapping("/edit")
	public ApiResult editUser(@RequestBody @Validated UserVo user) {

		throw new BusinessException(I18nResourceCode.MODULE_USER_ERROR_EDIT_FAILED);

	}

}
