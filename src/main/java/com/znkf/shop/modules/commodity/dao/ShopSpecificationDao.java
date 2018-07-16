/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.commodity.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.commodity.entity.ShopSpecification;

/**
 * 商品对应规格DAO接口
 * @author 武海升
 * @version 2018-07-05
 */
@MyBatisDao
public interface ShopSpecificationDao extends CrudDao<ShopSpecification> {

    void deleteAllByCommodityId(Integer commodityId);
}