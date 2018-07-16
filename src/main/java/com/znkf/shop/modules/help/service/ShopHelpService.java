/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.help.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.modules.help.dao.ShopHelpDao;
import com.znkf.shop.modules.help.entity.ShopHelp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 帮助中心Service
 * @author 武海升
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class ShopHelpService extends CrudService<ShopHelpDao, ShopHelp> {

	public ShopHelp get(String id) {
		return super.get(id);
	}
	
	public List<ShopHelp> findList(ShopHelp shopHelp) {
		return super.findList(shopHelp);
	}
	
	public Page<ShopHelp> findPage(Page<ShopHelp> page, ShopHelp shopHelp) {
		return super.findPage(page, shopHelp);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopHelp shopHelp) {
		if(StringUtils.isBlank(shopHelp.getId())){
			shopHelp.setAddTime(new Date());
			shopHelp.setDeleted(0);
		}
		super.save(shopHelp);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopHelp shopHelp) {
		super.delete(shopHelp);
	}
	
}