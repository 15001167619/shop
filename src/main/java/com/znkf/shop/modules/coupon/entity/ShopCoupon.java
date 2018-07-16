/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.coupon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 优惠券Entity
 * @author 武海升
 * @version 2018-07-02
 */
public class ShopCoupon extends DataEntity<ShopCoupon> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String money;		// money
	private Integer status;		// 0 可用, 1 禁用, 2 删除
	private Integer couponType;		// 优惠券类型 0 现金抵付
	private Date addTime;		// add_time
	private Integer deleted;		// deleted
	
	public ShopCoupon() {
		super();
	}

	public ShopCoupon(String id){
		super(id);
	}

	@Length(min=1, max=60, message="名称长度必须介于 1 和 60 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
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

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
}