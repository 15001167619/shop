/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.comment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 评论表Entity
 * @author 武海升
 * @version 2018-07-04
 */
public class ShopComment extends DataEntity<ShopComment> {
	
	private static final long serialVersionUID = 1L;
	private Integer businessType;		// 业务类型 0 商品
	private Integer businessId;		// 业务Id
	private Integer visible;
	private String content;		// content
	private Integer userId;		// user_id
	private Integer hasPicture;		// has_picture
	private String picUrls;		// pic_urls
	private String userName;		// pic_urls
	private Integer star;		// 评分， 1-5
	private Date addTime;		// add_time
	private Integer deleted;		// deleted
	
	public ShopComment(Integer businessType,Integer businessId,Integer visible) {
		this.businessType = businessType;
		this.businessId = businessId;
		this.visible = visible;
	}

	public ShopComment(Integer businessType,Integer businessId,String content) {
		this.businessType = businessType;
		this.businessId = businessId;
		this.visible = 0;
		this.content = content;
		this.addTime = new Date();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ShopComment() {
		super();
	}

	public ShopComment(String id){
		super(id);
	}

	@NotNull(message="业务类型 0 商品不能为空")
	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	
	@NotNull(message="业务Id不能为空")
	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	
	@Length(min=0, max=1024, message="content长度必须介于 0 和 1024 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@NotNull(message="user_id不能为空")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@NotNull(message="has_picture不能为空")
	public Integer getHasPicture() {
		return hasPicture;
	}

	public void setHasPicture(Integer hasPicture) {
		this.hasPicture = hasPicture;
	}
	
	@Length(min=0, max=4, message="pic_urls长度必须介于 0 和 4 之间")
	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}
	
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
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

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}
}