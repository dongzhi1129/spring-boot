package com.learn.cloud.authority.common.core;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

	@ExceptionHandler(BusinessException.class)
	public ApiResult businessExceptionHandler(final BusinessException businessException) {
		ApiResult apiResult = null;
		try {
			apiResult = ApiResult.failed();
		} catch (Exception e) {
			log.error("", e);
			apiResult = getLoacleInternalErrorApiResult();
		}
		return apiResult;

	}

	@ExceptionHandler(Exception.class)
	public ApiResult uncaughtExceptionHandler(Exception exception) {
		log.error("", exception);
		return getLoacleInternalErrorApiResult();
	}

	/**
	 * 参数校验异常
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = { BindException.class, MethodArgumentNotValidException.class,
			ConstraintViolationException.class })
	public ApiResult validaterExceptionHandler(final Exception exception) {
		ApiResult apiResult = null;

		try {
			String i18nMessage = null;
			apiResult = ApiResult.failed();
			if (exception instanceof MethodArgumentNotValidException) {
				i18nMessage = methodArgumentNotValidExceptionHandler((MethodArgumentNotValidException) exception);
			} else if (exception instanceof ConstraintViolationException) {
				i18nMessage = methodConstraintViolationExceptionHandler((ConstraintViolationException) exception);
			} else if (exception instanceof BindException) {
				i18nMessage = methodBindExceptionHandler((BindException) exception);
			}
			apiResult.setMessage(i18nMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
			apiResult = getLoacleInternalErrorApiResult();
			e.printStackTrace();
		}
		return apiResult;

	}

	private String methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
		String i18nMessage = exception.getBindingResult().getFieldError().getDefaultMessage();
		return i18nMessage;
	}

	private String methodConstraintViolationExceptionHandler(ConstraintViolationException exception) {
		String i18nMessage = null;
		if (!CollectionUtils.isEmpty(exception.getConstraintViolations())) {
			Iterator<ConstraintViolation<?>> ite = exception.getConstraintViolations().iterator();
			i18nMessage = ite.hasNext() ? ite.next().getMessage() : exception.getLocalizedMessage();
		} else {
			i18nMessage = exception.getLocalizedMessage();
		}
		return i18nMessage;
	}

	private String methodBindExceptionHandler(BindException exception) {
		FieldError fieldError = exception.getFieldError();
		String i18nMessage = fieldError.getDefaultMessage();
		return i18nMessage;
	}

	private ApiResult getLoacleInternalErrorApiResult() {
		ApiResult apiResult = ApiResult.internalError();
		return apiResult;
	}

}
