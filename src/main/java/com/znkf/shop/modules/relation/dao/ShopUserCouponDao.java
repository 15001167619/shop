/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.relation.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.relation.entity.ShopUserCoupon;

import java.util.List;

/**
 * 用户优惠券订单关联DAO接口
 * @author 武海升
 * @version 2018-07-02
 */
@MyBatisDao
public interface ShopUserCouponDao extends CrudDao<ShopUserCoupon> {

    List<ShopUserCoupon> findCouponList(ShopUserCoupon shopUserCoupon);
}