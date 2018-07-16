/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.address.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.address.entity.ShopAddress;
import com.znkf.shop.modules.address.service.ShopAddressService;
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
 * 收货地址Controller
 * @author 武海升
 * @version 2018-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/address/shopAddress")
public class ShopAddressController extends BaseController {

	@Autowired
	private ShopAddressService shopAddressService;
	
	@ModelAttribute
	public ShopAddress get(@RequestParam(required=false) String id) {
		ShopAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopAddressService.get(id);
		}
		if (entity == null){
			entity = new ShopAddress();
		}
		return entity;
	}
	
	@RequiresPermissions("address:shopAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopAddress shopAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopAddress> page = shopAddressService.findViewPage(new Page<ShopAddress>(request, response), shopAddress);
		model.addAttribute("page", page);
		return "modules/address/shopAddressList";
	}

	@RequiresPermissions("address:shopAddress:view")
	@RequestMapping(value = "form")
	public String form(ShopAddress shopAddress, Model model) {
		model.addAttribute("shopAddress", shopAddress);
		return "modules/address/shopAddressForm";
	}

	@RequiresPermissions("address:shopAddress:edit")
	@RequestMapping(value = "save")
	public String save(ShopAddress shopAddress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopAddress)){
			return form(shopAddress, model);
		}
		shopAddressService.save(shopAddress);
		addMessage(redirectAttributes, "保存收货地址成功");
		return "redirect:"+Global.getAdminPath()+"/address/shopAddress/?repage";
	}
	
	@RequiresPermissions("address:shopAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopAddress shopAddress, RedirectAttributes redirectAttributes) {
		shopAddressService.delete(shopAddress);
		addMessage(redirectAttributes, "删除收货地址成功");
		return "redirect:"+Global.getAdminPath()+"/address/shopAddress/?repage";
	}

}