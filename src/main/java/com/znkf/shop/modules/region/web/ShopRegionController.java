/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.region.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.region.entity.ShopRegion;
import com.znkf.shop.modules.region.service.ShopRegionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 行政区域Controller
 * @author 武海升
 * @version 2018-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/region/shopRegion")
public class ShopRegionController extends BaseController {

	@Autowired
	private ShopRegionService shopRegionService;
	
	@ModelAttribute
	public ShopRegion get(@RequestParam(required=false) String id) {
		ShopRegion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopRegionService.get(id);
		}
		if (entity == null){
			entity = new ShopRegion();
		}
		return entity;
	}
	
	@RequiresPermissions("region:shopRegion:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopRegion shopRegion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopRegion> page = shopRegionService.findPage(new Page<ShopRegion>(request, response), shopRegion); 
		model.addAttribute("page", page);
		return "modules/region/shopRegionList";
	}

	@RequiresPermissions("region:shopRegion:view")
	@RequestMapping(value = "form")
	public String form(ShopRegion shopRegion, Model model) {
		model.addAttribute("shopRegion", shopRegion);
		return "modules/region/shopRegionForm";
	}

	@RequiresPermissions("region:shopRegion:edit")
	@RequestMapping(value = "save")
	public String save(ShopRegion shopRegion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopRegion)){
			return form(shopRegion, model);
		}
		shopRegionService.save(shopRegion);
		addMessage(redirectAttributes, "保存行政区域成功");
		return "redirect:"+Global.getAdminPath()+"region/shopRegion/?repage";
	}
	
	@RequiresPermissions("region:shopRegion:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopRegion shopRegion, RedirectAttributes redirectAttributes) {
		shopRegionService.delete(shopRegion);
		addMessage(redirectAttributes, "删除行政区域成功");
		return "redirect:"+Global.getAdminPath()+"/region/shopRegion/?repage";
	}

}