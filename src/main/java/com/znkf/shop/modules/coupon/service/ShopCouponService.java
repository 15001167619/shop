/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.coupon.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.modules.coupon.dao.ShopCouponDao;
import com.znkf.shop.modules.coupon.entity.ShopCoupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 优惠券Service
 * @author 武海升
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class ShopCouponService extends CrudService<ShopCouponDao, ShopCoupon> {

	public ShopCoupon get(String id) {
		return super.get(id);
	}
	
	public List<ShopCoupon> findList(ShopCoupon shopCoupon) {
		return super.findList(shopCoupon);
	}
	
	public Page<ShopCoupon> findPage(Page<ShopCoupon> page, ShopCoupon shopCoupon) {
		return super.findPage(page, shopCoupon);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopCoupon shopCoupon) {
		if(StringUtils.isBlank(shopCoupon.getId())){
			shopCoupon.setDeleted(0);
			shopCoupon.setAddTime(new Date());
			shopCoupon.setStatus(0);
		}
		super.save(shopCoupon);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopCoupon shopCoupon) {
		super.delete(shopCoupon);
	}

	@Transactional(readOnly = false)
	public void forbidden(Integer id) {
		dao.forbidden(id);
	}

	@Transactional(readOnly = false)
	public void enable(Integer id) {
		dao.enable(id);
	}
}