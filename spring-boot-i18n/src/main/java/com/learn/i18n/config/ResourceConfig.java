package com.learn.i18n.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ResourceConfig {

	@Bean
	@Primary
	ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
		bundleMessageSource.setDefaultEncoding("UTF-8");
		// 指定国际化资源目录,其中i18n/error为文件夹，ValidationMessages为国际化文件前缀
		bundleMessageSource.setBasenames("i18n/error/ValidationMessages");
		bundleMessageSource.setCacheMillis(10);
		return bundleMessageSource;
	}
}
