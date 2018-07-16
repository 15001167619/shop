/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.region.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.modules.region.dao.ShopRegionDao;
import com.znkf.shop.modules.region.entity.ShopRegion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 行政区域Service
 * @author 武海升
 * @version 2018-06-29
 */
@Service
@Transactional(readOnly = true)
public class ShopRegionService extends CrudService<ShopRegionDao, ShopRegion> {

	public ShopRegion get(String id) {
		return super.get(id);
	}
	
	public List<ShopRegion> findList(ShopRegion shopRegion) {
		return super.findList(shopRegion);
	}
	
	public Page<ShopRegion> findPage(Page<ShopRegion> page, ShopRegion shopRegion) {
		return super.findPage(page, shopRegion);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopRegion shopRegion) {
		super.save(shopRegion);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopRegion shopRegion) {
		super.delete(shopRegion);
	}
	
}