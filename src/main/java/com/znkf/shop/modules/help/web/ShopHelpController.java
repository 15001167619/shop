/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.help.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.help.entity.ShopHelp;
import com.znkf.shop.modules.help.service.ShopHelpService;
import org.apache.commons.lang3.StringEscapeUtils;
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
 * 帮助中心Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/help/shopHelp")
public class ShopHelpController extends BaseController {

	@Autowired
	private ShopHelpService shopHelpService;
	
	@ModelAttribute
	public ShopHelp get(@RequestParam(required=false) String id) {
		ShopHelp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopHelpService.get(id);
		}
		if (entity == null){
			entity = new ShopHelp();
		}
		return entity;
	}
	
	@RequiresPermissions("help:shopHelp:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopHelp shopHelp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopHelp> page = shopHelpService.findPage(new Page<ShopHelp>(request, response), shopHelp); 
		model.addAttribute("page", page);
		return "modules/help/shopHelpList";
	}

	@RequiresPermissions("help:shopHelp:view")
	@RequestMapping(value = "form")
	public String form(ShopHelp shopHelp, Model model) {
		model.addAttribute("shopHelp", shopHelp);
		return "modules/help/shopHelpForm";
	}

	@RequiresPermissions("help:shopHelp:edit")
	@RequestMapping(value = "save")
	public String save(ShopHelp shopHelp, RedirectAttributes redirectAttributes) {
		shopHelp.setQuestion(StringEscapeUtils.unescapeHtml4(shopHelp.getQuestion()));
		shopHelp.setSolution(StringEscapeUtils.unescapeHtml4(shopHelp.getSolution()));
		shopHelpService.save(shopHelp);
		addMessage(redirectAttributes, "保存帮助中心成功");
		return "redirect:"+Global.getAdminPath()+"/help/shopHelp/?repage";
	}
	
	@RequiresPermissions("help:shopHelp:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopHelp shopHelp, RedirectAttributes redirectAttributes) {
		shopHelpService.delete(shopHelp);
		addMessage(redirectAttributes, "删除帮助中心成功");
		return "redirect:"+Global.getAdminPath()+"/help/shopHelp/?repage";
	}

}