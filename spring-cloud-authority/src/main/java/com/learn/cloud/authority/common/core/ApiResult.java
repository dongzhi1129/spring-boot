package com.learn.cloud.authority.common.core;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** i18n message key **/
	@NotNull
	private Integer code;

	/** 返回信息 **/
	private String message;

	/** 返回数据 **/
	private Object rows;

	private Page page;

	public ApiResult(Integer code, @NotNull String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ApiResult(Integer code, @NotNull String message, Page page) {
		super();
		this.code = code;
		this.message = message;
		this.page = page;
	}

	private ApiResult(MessageEnum messageEnum) {
		this.code = messageEnum.getCode();
		this.message = messageEnum.getMessage();
	}

	public static ApiResult success() {
		ApiResult apiResult = new ApiResult(MessageEnum.MESSAGE_SUCCESS);
		return apiResult;
	}

	public static ApiResult failed() {
		ApiResult apiResult = new ApiResult(MessageEnum.MESSAGE_FAILED);
		return apiResult;
	}

	public static ApiResult internalError() {
		ApiResult apiResult = new ApiResult(MessageEnum.MESSAGE_INTERAL_ERROR);
		return apiResult;
	}

	public ApiResult setMessage(String message) {
		this.message = message;
		return this;
	}

	public ApiResult setCode(Integer code) {
		this.code = code;
		return this;
	}

	public ApiResult setRows(Object rows) {
		this.rows = rows;
		return this;
	}

	public ApiResult setPage(Page page) {
		this.page = page;
		return this;
	}

	@Setter
	@Getter
	@NoArgsConstructor
	public static class Page {

		private Integer pageSize;

		private Integer pageNumber;

		private Integer totalPage;

		private Integer totalRows;

		public Page(Integer pageSize, Integer pageNumber, Integer totalPage, Integer totalRows) {
			super();
			this.pageSize = pageSize;
			this.pageNumber = pageNumber;
			this.totalPage = totalPage;
			this.totalRows = totalRows;
		}

		public Page(Integer pageSize, Integer pageNumber, Integer totalPage) {
			super();
			this.pageSize = pageSize;
			this.pageNumber = pageNumber;
			this.totalPage = totalPage;
		}

	}

}
