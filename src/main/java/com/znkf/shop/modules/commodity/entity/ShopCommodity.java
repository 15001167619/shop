/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.commodity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 商品Entity
 * @author 武海升
 * @version 2018-07-02
 */
public class ShopCommodity extends DataEntity<ShopCommodity> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商品名称
	private String englishName;		// 商品名称
	private String brief;		// 商品简介
	private Integer isOnSale;		// 是否在售
	private Double counterPrice;		// 专柜价格
	private String listPicUrl;		// 商品列表图
	private String unit;		// 商品单位
	private Double retailPrice;		// 零售价格
	private String description;		// 商品详细介绍
	private Date addTime;		// add_time
	private Integer deleted;		// deleted

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public ShopCommodity() {
		super();
	}

	public ShopCommodity(Integer isOnSale) {
		this.isOnSale = isOnSale;
	}

	public ShopCommodity(String id){
		super(id);
	}


	@Length(min=1, max=120, message="商品名称长度必须介于 1 和 120 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="商品简介长度必须介于 0 和 255 之间")
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	public Integer getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(Integer isOnSale) {
		this.isOnSale = isOnSale;
	}
	
	public Double getCounterPrice() {
		return counterPrice;
	}

	public void setCounterPrice(Double counterPrice) {
		this.counterPrice = counterPrice;
	}
	
	@Length(min=0, max=255, message="商品列表图长度必须介于 0 和 255 之间")
	public String getListPicUrl() {
		return listPicUrl;
	}

	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}
	
	@Length(min=0, max=45, message="商品单位长度必须介于 0 和 45 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	@Length(min=0, max=1000, message="商品详细介绍长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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