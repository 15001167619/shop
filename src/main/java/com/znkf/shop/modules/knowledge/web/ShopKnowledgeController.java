/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.knowledge.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.knowledge.entity.ShopKnowledge;
import com.znkf.shop.modules.knowledge.service.ShopKnowledgeService;
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
 * 常识Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/knowledge/shopKnowledge")
public class ShopKnowledgeController extends BaseController {

	@Autowired
	private ShopKnowledgeService shopKnowledgeService;
	
	@ModelAttribute
	public ShopKnowledge get(@RequestParam(required=false) String id) {
		ShopKnowledge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopKnowledgeService.get(id);
		}
		if (entity == null){
			entity = new ShopKnowledge();
		}
		return entity;
	}
	
	@RequiresPermissions("knowledge:shopKnowledge:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopKnowledge shopKnowledge, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopKnowledge> page = shopKnowledgeService.findPage(new Page<ShopKnowledge>(request, response), shopKnowledge); 
		model.addAttribute("page", page);
		return "modules/knowledge/shopKnowledgeList";
	}

	@RequiresPermissions("knowledge:shopKnowledge:view")
	@RequestMapping(value = "form")
	public String form(ShopKnowledge shopKnowledge, Model model) {
		model.addAttribute("shopKnowledge", shopKnowledge);
		return "modules/knowledge/shopKnowledgeForm";
	}

	@RequiresPermissions("knowledge:shopKnowledge:edit")
	@RequestMapping(value = "save")
	public String save(ShopKnowledge shopKnowledge, Model model, RedirectAttributes redirectAttributes) {
		shopKnowledge.setKnowledgeDesc(StringEscapeUtils.unescapeHtml4(shopKnowledge.getKnowledgeDesc()));
		shopKnowledgeService.save(shopKnowledge);
		addMessage(redirectAttributes, "保存常识成功");
		return "redirect:"+Global.getAdminPath()+"/knowledge/shopKnowledge/?repage";
	}
	
	@RequiresPermissions("knowledge:shopKnowledge:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopKnowledge shopKnowledge, RedirectAttributes redirectAttributes) {
		shopKnowledgeService.delete(shopKnowledge);
		addMessage(redirectAttributes, "删除常识成功");
		return "redirect:"+Global.getAdminPath()+"/knowledge/shopKnowledge/?repage";
	}

}