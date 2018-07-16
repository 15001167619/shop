/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.comment.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.comment.entity.ShopComment;

import java.util.List;

/**
 * 评论表DAO接口
 * @author 武海升
 * @version 2018-07-04
 */
@MyBatisDao
public interface ShopCommentDao extends CrudDao<ShopComment> {

    void forbidden(Integer id);

    void enable(Integer id);

    List<ShopComment> findShopCommentList(ShopComment shopComment);
}