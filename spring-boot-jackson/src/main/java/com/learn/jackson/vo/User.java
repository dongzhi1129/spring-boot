package com.learn.jackson.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	private Integer id;
	
	private String name;
	
	private String sex;
	
	private String adress;
	
	private String phoneNumber;
	
	private Date gmtCreate;
	
	private Date gmtModified;

}
