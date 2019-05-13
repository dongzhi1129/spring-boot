package com.learn.jackson.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfo extends AbstractBaseVo {
	
	private Integer id;

	private String companyName;

	private String companyAdress;

	private String wife;

	private String tag;

}
