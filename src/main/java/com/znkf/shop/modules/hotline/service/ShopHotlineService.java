/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.hotline.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.modules.hotline.dao.ShopHotlineDao;
import com.znkf.shop.modules.hotline.entity.ShopHotline;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 客服热线表Service
 * @author 武海升
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class ShopHotlineService extends CrudService<ShopHotlineDao, ShopHotline> {

	public ShopHotline get(String id) {
		return super.get(id);
	}
	
	public List<ShopHotline> findList(ShopHotline shopHotline) {
		return super.findList(shopHotline);
	}
	
	public Page<ShopHotline> findPage(Page<ShopHotline> page, ShopHotline shopHotline) {
		return super.findPage(page, shopHotline);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopHotline shopHotline) {
		if(StringUtils.isBlank(shopHotline.getId())){
			shopHotline.setAddTime(new Date());
			shopHotline.setStatus(0);
			shopHotline.setDeleted(0);
		}
		super.save(shopHotline);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopHotline shopHotline) {
		super.delete(shopHotline);
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