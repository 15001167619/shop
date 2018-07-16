/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.feedback.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.feedback.entity.ShopFeedback;
import com.znkf.shop.modules.feedback.service.ShopFeedbackService;
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
 * 意见反馈Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/feedback/shopFeedback")
public class ShopFeedbackController extends BaseController {

	@Autowired
	private ShopFeedbackService shopFeedbackService;
	
	@ModelAttribute
	public ShopFeedback get(@RequestParam(required=false) String id) {
		ShopFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopFeedbackService.get(id);
		}
		if (entity == null){
			entity = new ShopFeedback();
		}
		return entity;
	}
	
	@RequiresPermissions("feedback:shopFeedback:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopFeedback shopFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopFeedback> page = shopFeedbackService.findPage(new Page<ShopFeedback>(request, response), shopFeedback); 
		model.addAttribute("page", page);
		return "modules/feedback/shopFeedbackList";
	}

	@RequiresPermissions("feedback:shopFeedback:view")
	@RequestMapping(value = "form")
	public String form(ShopFeedback shopFeedback, Model model) {
		model.addAttribute("shopFeedback", shopFeedback);
		return "modules/feedback/shopFeedbackForm";
	}

	@RequiresPermissions("feedback:shopFeedback:edit")
	@RequestMapping(value = "save")
	public String save(ShopFeedback shopFeedback, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopFeedback)){
			return form(shopFeedback, model);
		}
		shopFeedbackService.save(shopFeedback);
		addMessage(redirectAttributes, "保存意见反馈成功");
		return "redirect:"+Global.getAdminPath()+"/feedback/shopFeedback/?repage";
	}
	
	@RequiresPermissions("feedback:shopFeedback:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopFeedback shopFeedback, RedirectAttributes redirectAttributes) {
		shopFeedbackService.delete(shopFeedback);
		addMessage(redirectAttributes, "删除意见反馈成功");
		return "redirect:"+Global.getAdminPath()+"/feedback/shopFeedback/?repage";
	}

}