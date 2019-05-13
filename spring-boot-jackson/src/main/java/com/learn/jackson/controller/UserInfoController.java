package com.learn.jackson.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jackson.common.annotation.JacksonFilter;
import com.learn.jackson.common.annotation.JacksonFilter.JscksonFilterType;
import com.learn.jackson.common.annotation.JacksonFilters;
import com.learn.jackson.common.core.ApiResult;
import com.learn.jackson.vo.User;
import com.learn.jackson.vo.UserInfo;

@RestController
@RequestMapping("/user/info")
public class UserInfoController {

	@PostMapping("/add")
	@JacksonFilters(value= {@JacksonFilter(exclude= {"id","tag","pageSize"},value=UserInfo.class,type=JscksonFilterType.REQUEST)})
	public ApiResult addUserInfo(@RequestBody UserInfo userInfo) {
		ApiResult apiResult = ApiResult.SUCCESS();
		List<UserInfo> infos = new ArrayList<UserInfo>();
		infos.add(userInfo);
		apiResult.setRows(infos);
		return apiResult;

	}

	@JacksonFilter(exclude= {"id"},value=UserInfo.class,type=JscksonFilterType.REQUEST)
	@PostMapping("/insert")
	public ApiResult addUserInfos(@RequestBody List<UserInfo> userInfos) {
		ApiResult apiResult = ApiResult.SUCCESS();
		List<UserInfo> infos = new ArrayList<UserInfo>();
		infos.addAll(userInfos);
		apiResult.setRows(infos);
		return apiResult;

	}

	@PostMapping("/list")
	@JacksonFilter(exclude= {"id"},value=User.class,type=JscksonFilterType.REQUEST)
	@JacksonFilter(include= {"id"},value=UserInfo.class,type=JscksonFilterType.RESPONSE)
	public ApiResult listUserInfo(@RequestBody User user) {
		System.out.println("==============>"+ user.toString());
		ApiResult apiResult = ApiResult.SUCCESS();
		List<UserInfo> infos = new ArrayList<UserInfo>();
		UserInfo info1 = new UserInfo();
		info1.setCompanyAdress("sum");
		info1.setCompanyName("ddd");
		info1.setId(1);
		info1.setTag("wd");
		info1.setWife("zh");
		info1.setPageNumber(1);
		info1.setPageSize(1);
		infos.add(info1);

		UserInfo info2 = new UserInfo();
		info2.setCompanyAdress("sum");
		info2.setCompanyName("ddd");
		info2.setId(1);
		info2.setTag("wd");
		info2.setWife("zh");
		info2.setPageNumber(1);
		info2.setPageSize(1);
		infos.add(info2);

		apiResult.setRows(infos);
		return apiResult;

	}

}
