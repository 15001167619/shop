/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.feedback.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.modules.feedback.dao.ShopFeedbackDao;
import com.znkf.shop.modules.feedback.entity.ShopFeedback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 意见反馈Service
 * @author 武海升
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class ShopFeedbackService extends CrudService<ShopFeedbackDao, ShopFeedback> {

	public ShopFeedback get(String id) {
		return super.get(id);
	}
	
	public List<ShopFeedback> findList(ShopFeedback shopFeedback) {
		return super.findList(shopFeedback);
	}
	
	public Page<ShopFeedback> findPage(Page<ShopFeedback> page, ShopFeedback shopFeedback) {
		return super.findPage(page, shopFeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopFeedback shopFeedback) {
		super.save(shopFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopFeedback shopFeedback) {
		super.delete(shopFeedback);
	}
	
}