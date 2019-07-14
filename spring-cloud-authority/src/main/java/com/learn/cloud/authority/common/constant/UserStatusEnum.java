package com.learn.cloud.authority.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

	USER_STATUS_UNLOCK((byte) 0, "unlock"),

	USER_STATUS_LOCKED((byte) 1, "locked"),

	USER_STATUS_UNKNOW((byte) -1, "unknow");

	private byte status;

	private String description;

	public static UserStatusEnum valueOf(byte status) {

		for (UserStatusEnum statusEnum : UserStatusEnum.values()) {
			if (status == statusEnum.getStatus()) {
				return statusEnum;
			}
		}
		return USER_STATUS_UNKNOW;
	}

}
