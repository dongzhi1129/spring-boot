package com.learn.i18n.config;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {

	@Resource
	private ResourceBundleMessageSource resourceBundleMessageSource;

	/*
	 * @Bean public Validator validator() throws Exception {
	 * 
	 * ValidatorFactory factory =
	 * Validation.byProvider(HibernateValidator.class).configure()
	 * .messageInterpolator(new ResourceBundleMessageInterpolator( new
	 * PlatformResourceBundleLocator("i18n/error/ValidationMessages")))
	 * .addProperty("hibernate.validator.fail_fast",
	 * "true").buildValidatorFactory();
	 * 
	 * return factory.getValidator(); }
	 */

	@Bean
	public Validator validator() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(resourceBundleMessageSource);
		return validator;
	}

}
