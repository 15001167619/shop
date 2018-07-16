/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.address.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.address.entity.ShopAddress;

import java.util.List;

/**
 * 收货地址DAO接口
 * @author 武海升
 * @version 2018-07-05
 */
@MyBatisDao
public interface ShopAddressDao extends CrudDao<ShopAddress> {

    List<ShopAddress> findAddressList(ShopAddress shopAddress);

    void delAddress(Integer addressId, Integer userId);

    void setDefaultValueZero(Integer userId);

    List<ShopAddress> findViewList(ShopAddress shopAddress);

    ShopAddress getAddressDetails(Integer addressId, Integer userId);
}