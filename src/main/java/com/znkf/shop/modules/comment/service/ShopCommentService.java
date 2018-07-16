/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.comment.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.modules.comment.dao.ShopCommentDao;
import com.znkf.shop.modules.comment.entity.ShopComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 评论表Service
 * @author 武海升
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class ShopCommentService extends CrudService<ShopCommentDao, ShopComment> {

	public ShopComment get(String id) {
		return super.get(id);
	}
	
	public List<ShopComment> findList(ShopComment shopComment) {
		return super.findList(shopComment);
	}
	
	public Page<ShopComment> findPage(Page<ShopComment> page, ShopComment shopComment) {
		return super.findPage(page, shopComment);
	}

	public Page<ShopComment> findShopCommentPage(Page<ShopComment> page, ShopComment shopComment) {
		shopComment.setPage(page);
		page.setList(dao.findShopCommentList(shopComment));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(ShopComment shopComment) {
		super.save(shopComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopComment shopComment) {
		super.delete(shopComment);
	}

	@Transactional(readOnly = false)
	public void forbidden(Integer id) {
		dao.forbidden(id);
	}

	@Transactional(readOnly = false)
	public void enable(Integer id) {
		dao.enable(id);
	}
	
}