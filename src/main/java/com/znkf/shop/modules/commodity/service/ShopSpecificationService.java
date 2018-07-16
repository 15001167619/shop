/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.commodity.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.modules.commodity.dao.ShopSpecificationDao;
import com.znkf.shop.modules.commodity.entity.ShopSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品对应规格Service
 * @author 武海升
 * @version 2018-07-05
 */
@Service
@Transactional(readOnly = true)
public class ShopSpecificationService extends CrudService<ShopSpecificationDao, ShopSpecification> {

	public ShopSpecification get(String id) {
		return super.get(id);
	}
	
	public List<ShopSpecification> findList(ShopSpecification shopSpecification) {
		return super.findList(shopSpecification);
	}
	
	public Page<ShopSpecification> findPage(Page<ShopSpecification> page, ShopSpecification shopSpecification) {
		return super.findPage(page, shopSpecification);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopSpecification shopSpecification) {
		super.save(shopSpecification);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopSpecification shopSpecification) {
		super.delete(shopSpecification);
	}

	@Transactional(readOnly = false)
    public void deleteAllByCommodityId(Integer commodityId) {
		dao.deleteAllByCommodityId(commodityId);
    }
}