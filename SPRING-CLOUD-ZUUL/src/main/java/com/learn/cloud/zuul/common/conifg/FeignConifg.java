package com.learn.cloud.zuul.common.conifg;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConifg implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {
			return;
		}
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();
				Enumeration<String> values = request.getHeaders(name);
				while (values.hasMoreElements()) {
					String value = values.nextElement();
					template.header(name, value);
				}
			}
		}

	}
}
