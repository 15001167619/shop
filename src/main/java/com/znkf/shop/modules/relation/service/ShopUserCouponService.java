/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.relation.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.modules.relation.dao.ShopUserCouponDao;
import com.znkf.shop.modules.relation.entity.ShopUserCoupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户优惠券订单关联Service
 * @author 武海升
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class ShopUserCouponService extends CrudService<ShopUserCouponDao, ShopUserCoupon> {

	public ShopUserCoupon get(String id) {
		return super.get(id);
	}
	
	public List<ShopUserCoupon> findList(ShopUserCoupon shopUserCoupon) {
		return super.findList(shopUserCoupon);
	}
	
	public Page<ShopUserCoupon> findPage(Page<ShopUserCoupon> page, ShopUserCoupon shopUserCoupon) {
		return super.findPage(page, shopUserCoupon);
	}

	public Page<ShopUserCoupon> findCouponPage(Page<ShopUserCoupon> page, ShopUserCoupon shopUserCoupon) {
		shopUserCoupon.setPage(page);
		page.setList(dao.findCouponList(shopUserCoupon));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(ShopUserCoupon shopUserCoupon) {
		super.save(shopUserCoupon);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopUserCoupon shopUserCoupon) {
		super.delete(shopUserCoupon);
	}
	
}