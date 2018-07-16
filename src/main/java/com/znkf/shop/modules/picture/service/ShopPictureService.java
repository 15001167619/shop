/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.picture.service;

import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.service.CrudService;
import com.znkf.shop.modules.picture.dao.ShopPictureDao;
import com.znkf.shop.modules.picture.entity.ShopPicture;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 图片Service
 * @author 武海升
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class ShopPictureService extends CrudService<ShopPictureDao, ShopPicture> {

	public ShopPicture get(String id) {
		return super.get(id);
	}
	
	public List<ShopPicture> findList(ShopPicture shopPicture) {
		return super.findList(shopPicture);
	}
	
	public Page<ShopPicture> findPage(Page<ShopPicture> page, ShopPicture shopPicture) {
		return super.findPage(page, shopPicture);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopPicture shopPicture) {
		super.save(shopPicture);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopPicture shopPicture) {
		super.delete(shopPicture);
	}

	@Transactional(readOnly = false)
    public void deleteAllPicture(ShopPicture shopPicture) {
		dao.deleteAllPicture(shopPicture);
    }
}