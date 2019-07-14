package com.learn.cloud.authority.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {

	USER_TYPE((byte) 0, "普通用户");

	private byte code;

	private String description;

}
