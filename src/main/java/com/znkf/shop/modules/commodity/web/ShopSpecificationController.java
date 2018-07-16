/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.commodity.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.commodity.entity.ShopSpecification;
import com.znkf.shop.modules.commodity.service.ShopSpecificationService;
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
 * 商品对应规格Controller
 * @author 武海升
 * @version 2018-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/commodity/shopSpecification")
public class ShopSpecificationController extends BaseController {

	@Autowired
	private ShopSpecificationService shopSpecificationService;
	
	@ModelAttribute
	public ShopSpecification get(@RequestParam(required=false) String id) {
		ShopSpecification entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopSpecificationService.get(id);
		}
		if (entity == null){
			entity = new ShopSpecification();
		}
		return entity;
	}
	
	@RequiresPermissions("commodity:shopSpecification:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopSpecification shopSpecification, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopSpecification> page = shopSpecificationService.findPage(new Page<ShopSpecification>(request, response), shopSpecification); 
		model.addAttribute("page", page);
		return "modules/commodity/shopSpecificationList";
	}

	@RequiresPermissions("commodity:shopSpecification:view")
	@RequestMapping(value = "form")
	public String form(ShopSpecification shopSpecification, Model model) {
		model.addAttribute("shopSpecification", shopSpecification);
		return "modules/commodity/shopSpecificationForm";
	}

	@RequiresPermissions("commodity:shopSpecification:edit")
	@RequestMapping(value = "save")
	public String save(ShopSpecification shopSpecification, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopSpecification)){
			return form(shopSpecification, model);
		}
		shopSpecificationService.save(shopSpecification);
		addMessage(redirectAttributes, "保存商品对应规格成功");
		return "redirect:"+Global.getAdminPath()+"/commodity/shopSpecification/?repage";
	}
	
	@RequiresPermissions("commodity:shopSpecification:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopSpecification shopSpecification, RedirectAttributes redirectAttributes) {
		shopSpecificationService.delete(shopSpecification);
		addMessage(redirectAttributes, "删除商品对应规格成功");
		return "redirect:"+Global.getAdminPath()+"/commodity/shopSpecification/?repage";
	}

}