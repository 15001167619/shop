/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.knowledge.dao;

import com.znkf.shop.common.persistence.CrudDao;
import com.znkf.shop.common.persistence.annotation.MyBatisDao;
import com.znkf.shop.modules.knowledge.entity.ShopKnowledge;

/**
 * 常识DAO接口
 * @author 武海升
 * @version 2018-07-02
 */
@MyBatisDao
public interface ShopKnowledgeDao extends CrudDao<ShopKnowledge> {

    void setKnowledgeBrowseCount(Integer id);
}