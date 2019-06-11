package com.learn.jta.repository.model;

import java.io.Serializable;
import java.util.Date;

public class Repository implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column repository.id
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column repository.re_name
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	private String reName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column repository.stock
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	private Integer stock;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column repository.re_lock
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	private Integer reLock;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column repository.version
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	private Integer version;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column repository.gmt_create
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	private Date gmtCreate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column repository.gmt_modified
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	private Date gmtModified;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table repository
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column repository.id
	 * @return  the value of repository.id
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column repository.id
	 * @param id  the value for repository.id
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column repository.re_name
	 * @return  the value of repository.re_name
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public String getReName() {
		return reName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column repository.re_name
	 * @param reName  the value for repository.re_name
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public void setReName(String reName) {
		this.reName = reName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column repository.stock
	 * @return  the value of repository.stock
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column repository.stock
	 * @param stock  the value for repository.stock
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column repository.re_lock
	 * @return  the value of repository.re_lock
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public Integer getReLock() {
		return reLock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column repository.re_lock
	 * @param reLock  the value for repository.re_lock
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public void setReLock(Integer reLock) {
		this.reLock = reLock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column repository.version
	 * @return  the value of repository.version
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column repository.version
	 * @param version  the value for repository.version
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column repository.gmt_create
	 * @return  the value of repository.gmt_create
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column repository.gmt_create
	 * @param gmtCreate  the value for repository.gmt_create
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column repository.gmt_modified
	 * @return  the value of repository.gmt_modified
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column repository.gmt_modified
	 * @param gmtModified  the value for repository.gmt_modified
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table repository
	 * @mbg.generated  Tue Jun 11 23:50:30 CST 2019
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", reName=").append(reName);
		sb.append(", stock=").append(stock);
		sb.append(", reLock=").append(reLock);
		sb.append(", version=").append(version);
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}