package com.learn.cloud.authority.common.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageEnum {

	MESSAGE_SUCCESS(0, "success"), MESSAGE_FAILED(-1, "failed"), MESSAGE_INTERAL_ERROR(500, "interal error"),
	MESSAGE_TIME_OUT(-2, "timeout");

	private int code;

	private String message;

}
