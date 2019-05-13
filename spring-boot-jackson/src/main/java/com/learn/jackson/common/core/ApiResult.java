package com.learn.jackson.common.core;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {

	public static final int DEFAULT_STATUS_SUCCESS = 0;

	public static final int DEFAULT_STATUS_FAILED = -1;

	// 统一状态编码
	@NotNull
	private Integer status;

	// 返回信息描述
	private String message;

	// 错误信息编码，后台进行统一编码,message为国际化信息{code:message}。其中code为key,message为对应国际化信息
	private String code;

	// 后台信息数据返回
	private Object rows;

	// 分页信息
	private PageVo pageVo;

	/**
	 * @param status
	 * @param message
	 * @param code
	 */
	public ApiResult(Integer status, @NotNull String code) {
		super();
		this.status = status;
		this.code = code;
	}

	public ApiResult(Integer status, @NotNull String code, String message) {
		super();
		this.status = status;
		this.message = message;
		this.code = code;
	}

	public static ApiResult SUCCESS() {
		ApiResult apiResult = new ApiResult();
		apiResult.setStatus(0);
		apiResult.setCode("");
		return apiResult;
	}

	public static ApiResult FAILED() {
		ApiResult apiResult = new ApiResult();
		apiResult.setStatus(-1);
		apiResult.setCode("");
		return apiResult;
	}

	public static ApiResult INTERNAL_ERROR() {
		ApiResult apiResult = new ApiResult();
		return apiResult;
	}

	// 后台分页信息
	@SuppressWarnings("javadoc")
	@Getter
	@Setter
	@NoArgsConstructor
	public static class PageVo {

		// 每页记录数
		private Integer pageSize;
		// 当前页码
		private Integer pageNumber;
		// 总页数
		private Integer totalPage;
		// 总记录数
		private Integer totalRows;

		/**
		 * @param pageSize
		 * @param pageNumber
		 */
		public PageVo(Integer pageSize, Integer pageNumber) {
			super();
			this.pageSize = pageSize;
			this.pageNumber = pageNumber;
		}

		/**
		 * @param pageSize
		 * @param pageNumber
		 * @param totalPage
		 */
		public PageVo(Integer pageSize, Integer pageNumber, Integer totalPage) {
			super();
			this.pageSize = pageSize;
			this.pageNumber = pageNumber;
			this.totalPage = totalPage;
		}

	}
}
