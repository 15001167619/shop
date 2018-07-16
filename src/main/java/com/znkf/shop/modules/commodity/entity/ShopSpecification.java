/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.commodity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 商品对应规格Entity
 * @author 武海升
 * @version 2018-07-05
 */
public class ShopSpecification extends DataEntity<ShopSpecification> {
	
	private static final long serialVersionUID = 1L;
	private Integer commodityId;		// 商品Id
	private String color;		// 规格类型
	private Integer size;		// 规格参数值
	private String picUrl;		// pic_url
	private Date addTime;		// add_time

	public ShopSpecification(Integer commodityId,String color,Integer size,String picUrl){
		this.commodityId = commodityId;
		this.color = color;
		this.size = size;
		this.picUrl = picUrl;
		this.addTime = new Date();
	}
	public ShopSpecification(Integer commodityId){
		this.commodityId = commodityId;
	}

	public ShopSpecification() {
		super();
	}

	public ShopSpecification(String id){
		super(id);
	}

	@NotNull(message="商品Id不能为空")
	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Length(min=0, max=255, message="pic_url长度必须介于 0 和 255 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="add_time不能为空")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
}