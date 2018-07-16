/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.commodity.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.modules.commodity.dao.ShopCommodityDao;
import com.znkf.shop.modules.commodity.entity.ShopCommodity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 商品Service
 * @author 武海升
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class ShopCommodityService extends CrudService<ShopCommodityDao, ShopCommodity> {

	public ShopCommodity get(String id) {
		return super.get(id);
	}
	
	public List<ShopCommodity> findList(ShopCommodity shopCommodity) {
		return super.findList(shopCommodity);
	}
	
	public Page<ShopCommodity> findPage(Page<ShopCommodity> page, ShopCommodity shopCommodity) {
		return super.findPage(page, shopCommodity);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopCommodity shopCommodity) {
		if(StringUtils.isBlank(shopCommodity.getId())){
			shopCommodity.setAddTime(new Date());
			shopCommodity.setDeleted(0);
		}
		super.save(shopCommodity);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopCommodity shopCommodity) {
		super.delete(shopCommodity);
	}
	
}