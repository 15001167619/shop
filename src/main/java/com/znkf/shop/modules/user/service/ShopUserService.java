/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.user.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.modules.user.dao.ShopUserDao;
import com.znkf.shop.modules.user.entity.ShopUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 会员管理Service
 * @author 武海升
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class ShopUserService extends CrudService<ShopUserDao, ShopUser> {

	public ShopUser get(String id) {
		return super.get(id);
	}
	
	public List<ShopUser> findList(ShopUser shopUser) {
		return super.findList(shopUser);
	}
	
	public Page<ShopUser> findPage(Page<ShopUser> page, ShopUser shopUser) {
		return super.findPage(page, shopUser);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopUser shopUser) {
		if(StringUtils.isBlank(shopUser.getId())){
			shopUser.setAddTime(new Date());
		}
		super.save(shopUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopUser shopUser) {
		super.delete(shopUser);
	}

	public Integer getMobile(String mobile) {
		return dao.getMobile(mobile);
	}
}