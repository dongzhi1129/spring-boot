package com.learn.i18n.common.core;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class BusinessException extends RuntimeException {

	/* error code of i18n */
	@NonNull
	private I18nResourceCode i18nResourceCode;

	public BusinessException(@NonNull I18nResourceCode i18nResourceCode) {
		super();
		this.i18nResourceCode = i18nResourceCode;
	}

	public BusinessException(@NonNull I18nResourceCode i18nResourceCode, Throwable cause) {
		super(i18nResourceCode.getCode(), cause);
	}

}
