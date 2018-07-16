/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.help.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 帮助中心Entity
 * @author 武海升
 * @version 2018-07-02
 */
public class ShopHelp extends DataEntity<ShopHelp> {
	
	private static final long serialVersionUID = 1L;
	private String question;		// 问题
	private String solution;		// 解决方案
	private Date addTime;		// 插入时间
	private Integer deleted;		// deleted
	
	public ShopHelp() {
		super();
	}

	public ShopHelp(String id){
		super(id);
	}

	@Length(min=1, max=225, message="问题长度必须介于 1 和 225 之间")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Length(min=0, max=1000, message="解决方案长度必须介于 0 和 1000 之间")
	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
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