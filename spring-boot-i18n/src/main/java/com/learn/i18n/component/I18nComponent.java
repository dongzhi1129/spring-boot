package com.learn.i18n.component;

import java.util.Arrays;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class I18nComponent {

	private static final java.util.List<Locale> DEFAULT_ACCEPT_LOACLES = Arrays.asList(Locale.US, Locale.CHINESE);

	// 请求头信息，通过此解析locale
	public static final String HTTP_ACCEPT_LANGUAGE = "Accept-Language";

	@Autowired
	private ResourceBundleMessageSource bundleMessageSource;

	public Locale getLocale() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
		String locale = httpServletRequest.getHeader(HTTP_ACCEPT_LANGUAGE);
		if (StringUtils.isEmpty(locale)) {
			return Locale.US;
		}
		return Locale.lookup(Locale.LanguageRange.parse(locale), DEFAULT_ACCEPT_LOACLES);
	}

	public String getLoacleMessage(String propertyKey) {
		Locale locale = getLocale();
		String message = bundleMessageSource.getMessage(propertyKey, null, locale);
		return message;

	}
	
	public String getLoacleMessageWithPlaceHolder(String propertyKey,Object ... params) {
		Locale locale = getLocale();
		String message = bundleMessageSource.getMessage(propertyKey, params, locale);
		return message;

	}

}
