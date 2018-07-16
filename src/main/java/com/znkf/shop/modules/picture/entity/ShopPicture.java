/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.picture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 图片Entity
 * @author 武海升
 * @version 2018-07-04
 */
public class ShopPicture extends DataEntity<ShopPicture> {
	
	private static final long serialVersionUID = 1L;
	private Integer businessId;		// 业务ID
	private Integer businessType;		// 0 商品 1 意见反馈 2 常识
	private Integer pictureType;		// 0 列表图 1 详情页图片
	private String path;		// 路径
	private Date addTime;		// add_time


	public ShopPicture(Integer businessId,Integer businessType,Integer pictureType) {
		this.businessId = businessId;
		this.businessType = businessType;
		this.pictureType = pictureType;
	}

	public ShopPicture(Integer businessId,Integer businessType,Integer pictureType,String path) {
		this.businessId = businessId;
		this.businessType = businessType;
		this.pictureType = pictureType;
		this.path = path;
		this.addTime = new Date();
	}

	public ShopPicture() {
		super();
	}

	public ShopPicture(String id){
		super(id);
	}

	@NotNull(message="业务ID不能为空")
	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	
	@NotNull(message="0 商品 1 意见反馈 2 常识不能为空")
	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	
	@NotNull(message="0 列表图 1 详情页图片不能为空")
	public Integer getPictureType() {
		return pictureType;
	}

	public void setPictureType(Integer pictureType) {
		this.pictureType = pictureType;
	}
	
	@Length(min=1, max=225, message="路径长度必须介于 1 和 225 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	

}