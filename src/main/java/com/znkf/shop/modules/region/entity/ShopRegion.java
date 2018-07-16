/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.region.entity;

import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 行政区域Entity
 * @author 武海升
 * @version 2018-06-29
 */
public class ShopRegion extends DataEntity<ShopRegion> {
	
	private static final long serialVersionUID = 1L;
	private Integer pid;		// pid
	private String name;		// name
	private Integer type;		// 1, 2, 3, 4
	private Integer code;		// code
	
	public ShopRegion(Integer type,Integer pid) {
		this.type = type;
		this.pid = pid;
	}

	public ShopRegion(Integer type) {
		this.type = type;
	}

	public ShopRegion() {
		super();
	}

	public ShopRegion(String id){
		super(id);
	}

	@NotNull(message="pid不能为空")
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	@Length(min=1, max=120, message="name长度必须介于 1 和 120 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="1, 2, 3, 4不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@NotNull(message="code不能为空")
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}