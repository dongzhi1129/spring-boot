package com.learn.cloud.zuul.common.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.learn.cloud.zuul.common.constants.ZuulFilterTypeEnum;
import com.learn.cloud.zuul.common.core.ApiResult;
import com.learn.cloud.zuul.common.feign.UserService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AccessFilter extends ZuulFilter {

	@Autowired
	private UserService userService;
	
	private static String[] SERVICE_PREFIX = {"/authority"};
	

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return ZuulFilterTypeEnum.ZUUL_FILTER_TYPE_PRE.isShoudFilter();
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest httpRequest = requestAttributes.getRequest();
		String uri = httpRequest.getRequestURI();
		uri= uri.replaceFirst("/api", "");
		if(uri.contains(SERVICE_PREFIX[0])) {
			return null;
		}
		ApiResult apiResult = userService.checkPermission(uri);
		System.out.println(apiResult.toString());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return ZuulFilterTypeEnum.ZUUL_FILTER_TYPE_PRE.getType();
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return ZuulFilterTypeEnum.ZUUL_FILTER_TYPE_PRE.getOrder();
	}

}
