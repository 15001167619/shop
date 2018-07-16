/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.relation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户优惠券订单关联Entity
 * @author 武海升
 * @version 2018-07-02
 */
public class ShopUserCoupon extends DataEntity<ShopUserCoupon> {
	
	private static final long serialVersionUID = 1L;
	private Integer couponId;		// coupon_id
	private Integer userId;		// user_id
	private Integer orderId;		// order_id
	private Date usedTime;		// used_time
	private Date addTime;		// add_time
	private String deleted;		// deleted



	private String couponName;		// 名称
	private String money;		// money
	private Integer status;		// 0 可用, 1 禁用, 2 删除
	private Integer couponType;		// 优惠券类型 0 现金抵付

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public ShopUserCoupon(Integer userId) {
		this.userId = userId;
 	}
	public ShopUserCoupon() {
		super();
	}

	public ShopUserCoupon(String id){
		super(id);
	}

	@NotNull(message="coupon_id不能为空")
	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	
	@NotNull(message="user_id不能为空")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@NotNull(message="order_id不能为空")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="used_time不能为空")
	public Date getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@Length(min=0, max=1, message="deleted长度必须介于 0 和 1 之间")
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}