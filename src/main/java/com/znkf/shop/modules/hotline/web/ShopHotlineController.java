/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.hotline.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.hotline.entity.ShopHotline;
import com.znkf.shop.modules.hotline.service.ShopHotlineService;
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
 * 客服热线表Controller
 * @author 武海升
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hotline/shopHotline")
public class ShopHotlineController extends BaseController {

	@Autowired
	private ShopHotlineService shopHotlineService;
	
	@ModelAttribute
	public ShopHotline get(@RequestParam(required=false) String id) {
		ShopHotline entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopHotlineService.get(id);
		}
		if (entity == null){
			entity = new ShopHotline();
		}
		return entity;
	}
	
	@RequiresPermissions("hotline:shopHotline:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopHotline shopHotline, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopHotline> page = shopHotlineService.findPage(new Page<ShopHotline>(request, response), shopHotline); 
		model.addAttribute("page", page);
		return "modules/hotline/shopHotlineList";
	}

	@RequiresPermissions("hotline:shopHotline:view")
	@RequestMapping(value = "form")
	public String form(ShopHotline shopHotline, Model model) {
		model.addAttribute("shopHotline", shopHotline);
		return "modules/hotline/shopHotlineForm";
	}

	@RequiresPermissions("hotline:shopHotline:edit")
	@RequestMapping(value = "save")
	public String save(ShopHotline shopHotline, Model model, RedirectAttributes redirectAttributes) {
		shopHotlineService.save(shopHotline);
		addMessage(redirectAttributes, "保存客服热线成功");
		return "redirect:"+Global.getAdminPath()+"/hotline/shopHotline/?repage";
	}
	
	@RequiresPermissions("hotline:shopHotline:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopHotline shopHotline, RedirectAttributes redirectAttributes) {
		shopHotlineService.delete(shopHotline);
		addMessage(redirectAttributes, "删除客服热线成功");
		return "redirect:"+Global.getAdminPath()+"/hotline/shopHotline/?repage";
	}

	@RequiresPermissions("hotline:shopHotline:edit")
	@RequestMapping(value = "forbidden")
	public String forbidden(ShopHotline shopHotline, RedirectAttributes redirectAttributes) {
		shopHotlineService.forbidden(Integer.parseInt(shopHotline.getId()));
		addMessage(redirectAttributes, "禁用客服热线成功");
		return "redirect:"+Global.getAdminPath()+"/hotline/shopHotline/?repage";
	}

	@RequiresPermissions("hotline:shopHotline:edit")
	@RequestMapping(value = "enable")
	public String enable(ShopHotline shopHotline, RedirectAttributes redirectAttributes) {
		shopHotlineService.enable(Integer.parseInt(shopHotline.getId()));
		addMessage(redirectAttributes, "开启客服热线成功");
		return "redirect:"+Global.getAdminPath()+"/hotline/shopHotline/?repage";
	}

}