/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.region.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.region.entity.ShopRegion;

/**
 * 行政区域DAO接口
 * @author 武海升
 * @version 2018-06-29
 */
@MyBatisDao
public interface ShopRegionDao extends CrudDao<ShopRegion> {
	
}