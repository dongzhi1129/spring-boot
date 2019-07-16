package com.learn.cloud.zuul.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ZuulFilterTypeEnum {

	ZUUL_FILTER_TYPE_PRE("pre", 0, true),

	ZUUL_FILTER_TYPE_ROUTING("routing", 1, true),

	ZUUL_FILTER_TYPE_POST("post", 2, true),

	ZUUL_FILTER_TYPE_ERROR("error", 3, true);

	private String type;

	private int order;

	private boolean shoudFilter;

}
