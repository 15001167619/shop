/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.znkf.shop.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 会员管理Entity
 * @author 武海升
 * @version 2018-07-02
 */
public class ShopUser extends DataEntity<ShopUser> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// 用户名称
	private String password;		// password
	private Integer gender;		// 0 男， 1 女， 2 未知
	private Date birthday;		// birthday
	private Integer userLevel;		// 0 普通用户，1 VIP用户，2 高级VIP用户
	private String nickName;		// 用户昵称或网络名称
	private String mobile;		// 用户手机号码
	private String avatar;		// avatar
	private String weixinOpenid;		// weixin_openid
	private Integer status;		// 0 可用, 1 禁用, 2 删除
	private Date addTime;		// 注册时间
	private Integer deleted;		// deleted
	
	public ShopUser(String mobile,String weixinOpenid) {
		this.gender = 0;		// 0 男， 1 女， 2 未知
		this.userLevel = 0;		// 0 普通用户，1 VIP用户，2 高级VIP用户
		this.mobile = mobile;		// 用户手机号码
		this.weixinOpenid = weixinOpenid;		// weixin_openid
		this.status = 0;		// 0 可用, 1 禁用, 2 删除
		this.addTime = new Date();		// 注册时间
		this.deleted = 0;
	}
	public ShopUser() {
		super();
	}

	public ShopUser(String id){
		super(id);
	}

	@Length(min=1, max=60, message="用户名称长度必须介于 1 和 60 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=60, message="password长度必须介于 0 和 60 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotNull(message="0 男， 1 女， 2 未知不能为空")
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	
	@Length(min=1, max=60, message="用户昵称或网络名称长度必须介于 1 和 60 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Length(min=1, max=20, message="用户手机号码长度必须介于 1 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=1, max=255, message="avatar长度必须介于 1 和 255 之间")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Length(min=1, max=50, message="weixin_openid长度必须介于 1 和 50 之间")
	public String getWeixinOpenid() {
		return weixinOpenid;
	}

	public void setWeixinOpenid(String weixinOpenid) {
		this.weixinOpenid = weixinOpenid;
	}
	
	@NotNull(message="0 可用, 1 禁用, 2 删除不能为空")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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