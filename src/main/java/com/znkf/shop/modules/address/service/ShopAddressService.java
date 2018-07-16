/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.address.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.modules.address.dao.ShopAddressDao;
import com.znkf.shop.modules.address.entity.ShopAddress;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收货地址Service
 * @author 武海升
 * @version 2018-07-05
 */
@Service
@Transactional(readOnly = true)
public class ShopAddressService extends CrudService<ShopAddressDao, ShopAddress> {

	public ShopAddress get(String id) {
		return super.get(id);
	}
	
	public List<ShopAddress> findList(ShopAddress shopAddress) {
		return super.findList(shopAddress);
	}
	
	public Page<ShopAddress> findPage(Page<ShopAddress> page, ShopAddress shopAddress) {
		return super.findPage(page, shopAddress);
	}

	public Page<ShopAddress> findAddressPage(Page<ShopAddress> page, ShopAddress shopAddress) {
		shopAddress.setPage(page);
		page.setList(dao.findAddressList(shopAddress));
		return page;
	}

	public Page<ShopAddress> findViewPage(Page<ShopAddress> page, ShopAddress shopAddress) {
		shopAddress.setPage(page);
		page.setList(dao.findViewList(shopAddress));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(ShopAddress shopAddress) {
		super.save(shopAddress);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopAddress shopAddress) {
		super.delete(shopAddress);
	}
	
}