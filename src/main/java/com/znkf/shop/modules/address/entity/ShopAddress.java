/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.address.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 收货地址Entity
 * @author 武海升
 * @version 2018-07-05
 */
public class ShopAddress extends DataEntity<ShopAddress> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private Integer userId;		// user_id
	private Integer provinceId;		// province_id
	private Integer cityId;		// city_id
	private Integer areaId;		// area_id
	private String address;		// address
	private String mobile;		// mobile
	private Integer isDefault;		// is_default
	private Date addTime;		// add_time
	private Integer deleted;		// deleted

	private String areaName;
	private String cityName;
	private String provinceName;


	public ShopAddress(String name,Integer userId,Integer provinceId,Integer cityId,Integer areaId,String address,String mobile,Integer isDefault) {
		this.name = name;
		this.userId = userId;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaId = areaId;
		this.address = address;
		this.mobile = mobile;
		this.isDefault = isDefault;
		this.deleted = 0;
		this.addTime = new Date();
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public ShopAddress() {
		super();
	}

	public ShopAddress(String id){
		super(id);
	}
	public ShopAddress(Integer userId){
		this.userId = userId;
	}

	@Length(min=1, max=50, message="name长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="user_id不能为空")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@NotNull(message="province_id不能为空")
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	
	@NotNull(message="city_id不能为空")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	@NotNull(message="area_id不能为空")
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	@Length(min=0, max=500, message="address长度必须介于 0 和 500 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=1, max=20, message="mobile长度必须介于 1 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@NotNull(message="is_default不能为空")
	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="add_time不能为空")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@NotNull(message="deleted不能为空")
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public ShopAddress upDate(ShopAddress shopAddress,String name, Integer userId, Integer provinceId, Integer cityId, Integer areaId, String address, String mobile, Integer isDefault) {
		shopAddress.name= name;
		shopAddress.userId = userId;
		shopAddress.provinceId = provinceId;
		shopAddress.cityId = cityId;
		shopAddress.areaId = areaId;
		shopAddress.address = address;
		shopAddress.mobile = mobile;
		shopAddress.isDefault = isDefault;
		return shopAddress;
	}
}