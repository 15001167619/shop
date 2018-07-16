/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.order.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.order.entity.ShopOrder;

import java.util.List;

/**
 * 订单管理DAO接口
 * @author 武海升
 * @version 2018-07-02
 */
@MyBatisDao
public interface ShopOrderDao extends CrudDao<ShopOrder> {

    void deleteOrder(Integer userId, Integer orderId);

    void updateOrder(Integer userId, Integer orderId, Integer orderStatus);

    List<ShopOrder> findOrderPage(ShopOrder shopOrder);
}