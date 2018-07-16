/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.feedback.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 意见反馈Entity
 * @author 武海升
 * @version 2018-07-02
 */
public class ShopFeedback extends DataEntity<ShopFeedback> {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;		// 用户Id
	private String feedbackDesc;		// 意见反馈详情
	private String picUrl;		// 常识列表图
	private Date addTime;		// 插入时间
	private Integer deleted;		// deleted
	
	public ShopFeedback() {
		super();
	}
	public ShopFeedback(Integer userId,String feedbackDesc,String picUrl) {
		this.userId = userId;
		this.feedbackDesc = feedbackDesc;
		this.picUrl = picUrl;
		this.addTime = new Date();
		this.deleted = 0;
	}

	public ShopFeedback(String id){
		super(id);
	}

	@NotNull(message="用户Id不能为空")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=1000, message="意见反馈详情长度必须介于 0 和 1000 之间")
	public String getFeedbackDesc() {
		return feedbackDesc;
	}

	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}
	
	@Length(min=0, max=255, message="常识列表图长度必须介于 0 和 255 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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