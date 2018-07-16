/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.relation.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.relation.entity.ShopUserCoupon;
import com.znkf.shop.modules.relation.service.ShopUserCouponService;
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
 * 用户优惠券订单关联Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/relation/shopUserCoupon")
public class ShopUserCouponController extends BaseController {

	@Autowired
	private ShopUserCouponService shopUserCouponService;
	
	@ModelAttribute
	public ShopUserCoupon get(@RequestParam(required=false) String id) {
		ShopUserCoupon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopUserCouponService.get(id);
		}
		if (entity == null){
			entity = new ShopUserCoupon();
		}
		return entity;
	}
	
	@RequiresPermissions("relation:shopUserCoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopUserCoupon shopUserCoupon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopUserCoupon> page = shopUserCouponService.findPage(new Page<ShopUserCoupon>(request, response), shopUserCoupon); 
		model.addAttribute("page", page);
		return "modules/relation/shopUserCouponList";
	}

	@RequiresPermissions("relation:shopUserCoupon:view")
	@RequestMapping(value = "form")
	public String form(ShopUserCoupon shopUserCoupon, Model model) {
		model.addAttribute("shopUserCoupon", shopUserCoupon);
		return "modules/relation/shopUserCouponForm";
	}

	@RequiresPermissions("relation:shopUserCoupon:edit")
	@RequestMapping(value = "save")
	public String save(ShopUserCoupon shopUserCoupon, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopUserCoupon)){
			return form(shopUserCoupon, model);
		}
		shopUserCouponService.save(shopUserCoupon);
		addMessage(redirectAttributes, "保存用户优惠券订单关联成功");
		return "redirect:"+Global.getAdminPath()+"/relation/shopUserCoupon/?repage";
	}
	
	@RequiresPermissions("relation:shopUserCoupon:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopUserCoupon shopUserCoupon, RedirectAttributes redirectAttributes) {
		shopUserCouponService.delete(shopUserCoupon);
		addMessage(redirectAttributes, "删除用户优惠券订单关联成功");
		return "redirect:"+Global.getAdminPath()+"/relation/shopUserCoupon/?repage";
	}

}