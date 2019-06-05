package com.learn.asyc.core;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ApiResult implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final Integer STATUS_SUCCESS = 0;

	public static final Integer STATUS_FAILED = -1;

	/** 状态 **/
	private Integer status;

	/** 返回信息 **/
	private String message;

	/** 返回数据 **/
	private Object rows;
	
	public ApiResult(Integer status, @NonNull String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ApiResult(Integer status, @NonNull String message, Object rows) {
		super();
		this.status = status;
		this.message = message;
		this.rows = rows;
	}


	public ApiResult setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public ApiResult setMessage(String message) {
		this.message = message;
		return this;
	}


	public ApiResult setRows(Object rows) {
		this.rows = rows;
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
