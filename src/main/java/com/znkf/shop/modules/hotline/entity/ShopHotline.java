/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.hotline.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 客服热线表Entity
 * @author 武海升
 * @version 2018-07-04
 */
public class ShopHotline extends DataEntity<ShopHotline> {
	
	private static final long serialVersionUID = 1L;
	private String telephone;		// 客服电话
	private Date beginTime;		// 上班时间
	private Date endTime;		// 下班时间
	private Integer status;		// 0 可用, 1 禁用, 2 删除
	private Date addTime;		// add_time
	private Integer deleted;		// deleted
	
	public ShopHotline() {
		super();
	}

	public ShopHotline(Integer status) {
		this.status = status;
	}

	public ShopHotline(String id){
		super(id);
	}

	@Length(min=1, max=64, message="客服电话长度必须介于 1 和 64 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="上班时间不能为空")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="下班时间不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@NotNull(message="0 可用, 1 禁用, 2 删除不能为空")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
}