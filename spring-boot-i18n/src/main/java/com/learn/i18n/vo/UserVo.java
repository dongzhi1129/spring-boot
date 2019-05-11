package com.learn.i18n.vo;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import com.learn.i18n.common.core.I18nResourceCode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserVo {

	@Range(min = 1, max = Long.MAX_VALUE, message = I18nResourceCode.USER_ERROR_ID_IS_NOT_INT_RANGE)
	private Long id;

	@NotBlank(message = I18nResourceCode.USER_ERROR_NAME_MUST_NOT_BE_EMPTY)
	private String name;

	private String sex;

	private String phoneNumber;

	private String address;

}
