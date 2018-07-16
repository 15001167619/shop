/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.knowledge.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.modules.knowledge.dao.ShopKnowledgeDao;
import com.znkf.shop.modules.knowledge.entity.ShopKnowledge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 常识Service
 * @author 武海升
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class ShopKnowledgeService extends CrudService<ShopKnowledgeDao, ShopKnowledge> {

	public ShopKnowledge get(String id) {
		return super.get(id);
	}
	
	public List<ShopKnowledge> findList(ShopKnowledge shopKnowledge) {
		return super.findList(shopKnowledge);
	}
	
	public Page<ShopKnowledge> findPage(Page<ShopKnowledge> page, ShopKnowledge shopKnowledge) {
		return super.findPage(page, shopKnowledge);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopKnowledge shopKnowledge) {
		if(StringUtils.isBlank(shopKnowledge.getId())){
			shopKnowledge.setAddTime(new Date());
			shopKnowledge.setBrowseCount(0);
			shopKnowledge.setDeleted(0);
		}
		super.save(shopKnowledge);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopKnowledge shopKnowledge) {
		super.delete(shopKnowledge);
	}
	
}