/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.order.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.order.entity.ShopOrder;
import com.znkf.shop.modules.order.service.ShopOrderService;
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
 * 订单管理Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/order/shopOrder")
public class ShopOrderController extends BaseController {

	@Autowired
	private ShopOrderService shopOrderService;
	
	@ModelAttribute
	public ShopOrder get(@RequestParam(required=false) String id) {
		ShopOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopOrderService.get(id);
		}
		if (entity == null){
			entity = new ShopOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("order:shopOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopOrder shopOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopOrder> page = shopOrderService.findPage(new Page<ShopOrder>(request, response), shopOrder); 
		model.addAttribute("page", page);
		return "modules/order/shopOrderList";
	}

	@RequiresPermissions("order:shopOrder:view")
	@RequestMapping(value = "form")
	public String form(ShopOrder shopOrder, Model model) {
		model.addAttribute("shopOrder", shopOrder);
		return "modules/order/shopOrderForm";
	}

	@RequiresPermissions("order:shopOrder:edit")
	@RequestMapping(value = "save")
	public String save(ShopOrder shopOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopOrder)){
			return form(shopOrder, model);
		}
		shopOrderService.save(shopOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/order/shopOrder/?repage";
	}
	
	@RequiresPermissions("order:shopOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopOrder shopOrder, RedirectAttributes redirectAttributes) {
		shopOrderService.delete(shopOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/order/shopOrder/?repage";
	}

}