package com.learn.i18n.config;

import javax.annotation.Resource;
import javax.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {

	@Resource
	private ResourceBundleMessageSource resourceBundleMessageSource;

	@Bean
	public Validator validator() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(resourceBundleMessageSource);
		return validator;
	}

}
