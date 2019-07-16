package com.learn.cloud.zuul.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.learn.cloud.zuul.common.core.ApiResult;

@FeignClient(url = "localhost:7001",name="spring-cloud-authority")
public interface UserService {

	/**
	 * @param permission
	 * @Title: checkPermission
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return ApiResult
	 */
	@GetMapping("/authority/check/permission")
	public ApiResult checkPermission(@RequestParam(required = true, name = "permission") String permission);

}
