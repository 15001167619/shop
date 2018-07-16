/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.order.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.modules.order.dao.ShopOrderDao;
import com.znkf.shop.modules.order.entity.ShopOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单管理Service
 * @author 武海升
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class ShopOrderService extends CrudService<ShopOrderDao, ShopOrder> {

	public ShopOrder get(String id) {
		return super.get(id);
	}
	
	public List<ShopOrder> findList(ShopOrder shopOrder) {
		return super.findList(shopOrder);
	}
	
	public Page<ShopOrder> findPage(Page<ShopOrder> page, ShopOrder shopOrder) {
		return super.findPage(page, shopOrder);
	}

	public Page<ShopOrder> findOrderPage(Page<ShopOrder> page, ShopOrder shopOrder) {
		shopOrder.setPage(page);
		page.setList(dao.findOrderPage(shopOrder));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(ShopOrder shopOrder) {
		super.save(shopOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopOrder shopOrder) {
		super.delete(shopOrder);
	}
	
}