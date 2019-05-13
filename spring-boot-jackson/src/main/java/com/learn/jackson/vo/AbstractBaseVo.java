package com.learn.jackson.vo;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractBaseVo {
	
	private Integer pageSize;
	
	private Integer pageNumber;
	
	private Map<String, Integer> sortKeys;
	
	private Integer id;

}
