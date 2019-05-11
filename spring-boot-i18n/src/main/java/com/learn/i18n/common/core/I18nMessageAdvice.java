package com.learn.i18n.common.core;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.learn.i18n.component.I18nComponent;

@ControllerAdvice(annotations = RestController.class)
public class I18nMessageAdvice implements ResponseBodyAdvice<Object> {

	@Autowired
	private I18nComponent i18nComponent;

	private static final Class[] DEFUALT_SUPPORT_ANNOTATIONS = { RequestMapping.class, GetMapping.class,
			PostMapping.class, DeleteMapping.class, PutMapping.class };

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		AnnotatedElement annotatedElement = returnType.getAnnotatedElement();
		return Arrays.stream(DEFUALT_SUPPORT_ANNOTATIONS)
				.anyMatch(annotaion -> annotaion.isAnnotation() && annotatedElement.isAnnotationPresent(annotaion));
	}

	/**
	 * 返回信息統一封裝為ApiResult,并进行国际化信息处理
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// TODO Auto-generated method stub
		ApiResult apiResult = null;
		try {
			if (body == null) {
				apiResult = ApiResult.internalError();
			} else if (body instanceof ApiResult) {
				apiResult = (ApiResult) body;
			} else {
				// not support
				apiResult = ApiResult.internalError();
			}
			apiResult = apiResult.setMessage(i18nComponent.getLoacleMessage(apiResult.getCode()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiResult = ApiResult.internalError();
			apiResult.setMessage(i18nComponent.getLoacleMessage(apiResult.getCode()));
		}

		return apiResult;
	}

}
