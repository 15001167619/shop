/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.picture.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.picture.entity.ShopPicture;

/**
 * 图片DAO接口
 * @author 武海升
 * @version 2018-07-04
 */
@MyBatisDao
public interface ShopPictureDao extends CrudDao<ShopPicture> {

    void deleteAllPicture(ShopPicture shopPicture);
}