package com.learn.cloud.authority.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexTypeEnum {

	SEX_TYPE_MALE((byte) 0, "male"),

	SEX_TYPE_FEMALE((byte) 1, "female");

	private byte type;

	private String description;

}
