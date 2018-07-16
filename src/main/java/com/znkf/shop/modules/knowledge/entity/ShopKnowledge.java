/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.knowledge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 常识Entity
 * @author 武海升
 * @version 2018-07-02
 */
public class ShopKnowledge extends DataEntity<ShopKnowledge> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 标题
	private String knowledgeDesc;		// 常识详情
	private String listPicUrl;		// 常识列表图
	private String primaryPicUrl;		// 常识主图
	private Integer browseCount;		// 浏览量
	private Date addTime;		// 插入时间
	private Integer deleted;		// deleted
	
	public ShopKnowledge() {
		super();
	}

	public ShopKnowledge(String id){
		super(id);
	}

	@Length(min=1, max=120, message="标题长度必须介于 1 和 120 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getKnowledgeDesc() {
		return knowledgeDesc;
	}

	public void setKnowledgeDesc(String knowledgeDesc) {
		this.knowledgeDesc = knowledgeDesc;
	}
	
	@Length(min=0, max=255, message="常识列表图长度必须介于 0 和 255 之间")
	public String getListPicUrl() {
		return listPicUrl;
	}

	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}
	
	@Length(min=0, max=255, message="常识主图长度必须介于 0 和 255 之间")
	public String getPrimaryPicUrl() {
		return primaryPicUrl;
	}

	public void setPrimaryPicUrl(String primaryPicUrl) {
		this.primaryPicUrl = primaryPicUrl;
	}
	
	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
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