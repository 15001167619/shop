/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.coupon.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.coupon.entity.ShopCoupon;

/**
 * 优惠券DAO接口
 * @author 武海升
 * @version 2018-07-02
 */
@MyBatisDao
public interface ShopCouponDao extends CrudDao<ShopCoupon> {

    void forbidden(Integer id);

    void enable(Integer id);
}