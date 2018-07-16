/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.coupon.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.coupon.entity.ShopCoupon;
import com.znkf.shop.modules.coupon.service.ShopCouponService;
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
 * 优惠券Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/coupon/shopCoupon")
public class ShopCouponController extends BaseController {

	@Autowired
	private ShopCouponService shopCouponService;
	
	@ModelAttribute
	public ShopCoupon get(@RequestParam(required=false) String id) {
		ShopCoupon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopCouponService.get(id);
		}
		if (entity == null){
			entity = new ShopCoupon();
		}
		return entity;
	}
	
	@RequiresPermissions("coupon:shopCoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopCoupon shopCoupon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopCoupon> page = shopCouponService.findPage(new Page<ShopCoupon>(request, response), shopCoupon); 
		model.addAttribute("page", page);
		return "modules/coupon/shopCouponList";
	}

	@RequiresPermissions("coupon:shopCoupon:view")
	@RequestMapping(value = "form")
	public String form(ShopCoupon shopCoupon, Model model) {
		if(StringUtils.isBlank(shopCoupon.getId()))shopCoupon.setCouponType(0);
		model.addAttribute("shopCoupon", shopCoupon);
		return "modules/coupon/shopCouponForm";
	}

	@RequiresPermissions("coupon:shopCoupon:edit")
	@RequestMapping(value = "save")
	public String save(ShopCoupon shopCoupon, Model model, RedirectAttributes redirectAttributes) {
		shopCouponService.save(shopCoupon);
		addMessage(redirectAttributes, "保存优惠券成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/shopCoupon/?repage";
	}
	
	@RequiresPermissions("coupon:shopCoupon:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopCoupon shopCoupon, RedirectAttributes redirectAttributes) {
		shopCouponService.delete(shopCoupon);
		addMessage(redirectAttributes, "删除优惠券成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/shopCoupon/?repage";
	}

	@RequiresPermissions("coupon:shopCoupon:edit")
	@RequestMapping(value = "forbidden")
	public String forbidden(ShopCoupon shopCoupon, RedirectAttributes redirectAttributes) {
		shopCouponService.forbidden(Integer.parseInt(shopCoupon.getId()));
		addMessage(redirectAttributes, "禁用优惠券成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/shopCoupon/?repage";
	}

	@RequiresPermissions("coupon:shopCoupon:edit")
	@RequestMapping(value = "enable")
	public String enable(ShopCoupon shopCoupon, RedirectAttributes redirectAttributes) {
		shopCouponService.enable(Integer.parseInt(shopCoupon.getId()));
		addMessage(redirectAttributes, "启用优惠券成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/shopCoupon/?repage";
	}

}