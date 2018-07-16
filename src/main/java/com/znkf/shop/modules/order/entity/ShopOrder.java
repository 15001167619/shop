/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 订单管理Entity
 * @author 武海升
 * @version 2018-07-02
 */
public class ShopOrder extends DataEntity<ShopOrder> {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;		// user_id
	private Integer commodityId;		// order_sn
	private Integer orderStatus;		// 订单状态
	private String consignee;		// consignee
	private String mobile;		// mobile
	private String address;		// address
	private Double goodsPrice;		// 商品总费用
	private Double freightPrice;		// 配送费用
	private Double couponPrice;		// 优惠券减免
	private Double integralPrice;		// 用户积分减免
	private Double orderPrice;		// 订单费用， = goods_price + freight_price - coupon_price
	private Double actualPrice;		// 实付费用， = order_price - integral_price
	private String payId;		// 微信付款编号
	private Integer payStatus;		// 支付状态
	private Date payTime;		// 微信付款时间
	private String shipSn;		// 发货编号
	private String shipChannel;		// 发货快递公司
	private Date shipStartTime;		// 发货开始时间
	private Date shipEndTime;		// 发货结束时间
	private Date confirmTime;		// 用户确认收货时间
	private Date addTime;		// add_time
	private Integer deleted;		// deleted
	/*商品规格*/
	private String color;
	private String size;
	private String pic;

	/*商品信息*/
	private String name;
	private String brief;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public ShopOrder(Integer userId, String mobile, Integer commodityId, Double orderPrice, Double actualPrice, String consignee, String address,
					 String color, String size, String pic) {
		this.userId = userId;
		this.mobile = mobile;
		this.commodityId = commodityId;
		this.consignee = consignee;
		this.address = address;
		this.addTime = new Date();
		this.deleted = 0;
		this.orderStatus = 0;
		this.color = color;
		this.size = size;
		this.pic = pic;
		this.orderPrice = orderPrice;
		this.actualPrice = actualPrice;
	}
	public ShopOrder(Integer userId,Integer orderStatus) {
		this.userId = userId;
		this.orderStatus = orderStatus;
	}

	public ShopOrder() {
		super();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public ShopOrder(String id){
		super(id);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	@NotNull(message="订单状态不能为空")
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Length(min=1, max=45, message="consignee长度必须介于 1 和 45 之间")
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	@Length(min=1, max=45, message="mobile长度必须介于 1 和 45 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=1, max=45, message="address长度必须介于 1 和 45 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotNull(message="商品总费用不能为空")
	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	@NotNull(message="配送费用不能为空")
	public Double getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(Double freightPrice) {
		this.freightPrice = freightPrice;
	}
	
	@NotNull(message="优惠券减免不能为空")
	public Double getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(Double couponPrice) {
		this.couponPrice = couponPrice;
	}
	
	@NotNull(message="用户积分减免不能为空")
	public Double getIntegralPrice() {
		return integralPrice;
	}

	public void setIntegralPrice(Double integralPrice) {
		this.integralPrice = integralPrice;
	}
	
	@NotNull(message="订单费用， = goods_price + freight_price - coupon_price不能为空")
	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	@NotNull(message="实付费用， = order_price - integral_price不能为空")
	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	
	@Length(min=0, max=45, message="微信付款编号长度必须介于 0 和 45 之间")
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	@Length(min=0, max=45, message="发货编号长度必须介于 0 和 45 之间")
	public String getShipSn() {
		return shipSn;
	}

	public void setShipSn(String shipSn) {
		this.shipSn = shipSn;
	}
	
	@Length(min=0, max=45, message="发货快递公司长度必须介于 0 和 45 之间")
	public String getShipChannel() {
		return shipChannel;
	}

	public void setShipChannel(String shipChannel) {
		this.shipChannel = shipChannel;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShipStartTime() {
		return shipStartTime;
	}

	public void setShipStartTime(Date shipStartTime) {
		this.shipStartTime = shipStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShipEndTime() {
		return shipEndTime;
	}

	public void setShipEndTime(Date shipEndTime) {
		this.shipEndTime = shipEndTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
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