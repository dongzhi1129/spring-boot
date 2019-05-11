package com.learn.i18n.common.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum I18nResourceCode {

	COMMON_INFO_SUCCESS("common.info.success"),

	COMMON_INFO_FAIELD("common.info.failed"),
	
	COMMON_ERROR_INTERNAL("common.error.internal"),
	
	COMMON_ERROR_PARAMETER_VALIDATE("common.error.parameter.validate"),

	//business exception
	MODULE_USER_ERROR_EDIT_FAILED("module.user.error.edit.failed");
	
	
	
	private String code;
	
	//user parameter error code
	public static final String USER_ERROR_ID_IS_NOT_INT_RANGE = "{user.error.id.is.not.in.range}";
	
	public static final String USER_ERROR_NAME_MUST_NOT_BE_EMPTY = "{user.error.name.must.not.be.empty}";
	
	

}
