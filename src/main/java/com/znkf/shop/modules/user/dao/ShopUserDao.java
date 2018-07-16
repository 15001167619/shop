/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.user.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.user.entity.ShopUser;

/**
 * 会员管理DAO接口
 * @author 武海升
 * @version 2018-07-02
 */
@MyBatisDao
public interface ShopUserDao extends CrudDao<ShopUser> {

    Integer getMobile(String mobile);

    ShopUser getUserInfoByMobile(String mobile);
}