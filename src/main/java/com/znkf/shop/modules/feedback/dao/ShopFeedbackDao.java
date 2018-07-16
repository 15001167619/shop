/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.feedback.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.feedback.entity.ShopFeedback;

/**
 * 意见反馈DAO接口
 * @author 武海升
 * @version 2018-07-02
 */
@MyBatisDao
public interface ShopFeedbackDao extends CrudDao<ShopFeedback> {
	
}