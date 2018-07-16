/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.comment.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.comment.entity.ShopComment;
import com.znkf.shop.modules.comment.service.ShopCommentService;
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
 * 评论表Controller
 * @author 武海升
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/comment/shopComment")
public class ShopCommentController extends BaseController {

	@Autowired
	private ShopCommentService shopCommentService;
	
	@ModelAttribute
	public ShopComment get(@RequestParam(required=false) String id) {
		ShopComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopCommentService.get(id);
		}
		if (entity == null){
			entity = new ShopComment();
		}
		return entity;
	}
	
	@RequiresPermissions("comment:shopComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopComment shopComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopComment> page = shopCommentService.findPage(new Page<ShopComment>(request, response), shopComment); 
		model.addAttribute("page", page);
		return "modules/comment/shopCommentList";
	}

	@RequiresPermissions("comment:shopComment:view")
	@RequestMapping(value = "form")
	public String form(ShopComment shopComment, Model model) {
		model.addAttribute("shopComment", shopComment);
		return "modules/comment/shopCommentForm";
	}

	@RequiresPermissions("comment:shopComment:edit")
	@RequestMapping(value = "save")
	public String save(ShopComment shopComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopComment)){
			return form(shopComment, model);
		}
		shopCommentService.save(shopComment);
		addMessage(redirectAttributes, "保存评论成功");
		return "redirect:"+Global.getAdminPath()+"/comment/shopComment/?repage";
	}
	
	@RequiresPermissions("comment:shopComment:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopComment shopComment, RedirectAttributes redirectAttributes) {
		shopCommentService.delete(shopComment);
		addMessage(redirectAttributes, "删除评论成功");
		return "redirect:"+Global.getAdminPath()+"/comment/shopComment/?repage";
	}

	@RequiresPermissions("comment:shopComment:edit")
	@RequestMapping(value = "forbidden")
	public String forbidden(ShopComment shopComment, RedirectAttributes redirectAttributes) {
		shopCommentService.forbidden(Integer.parseInt(shopComment.getId()));
		addMessage(redirectAttributes, "设置评论不可见成功");
		return "redirect:"+Global.getAdminPath()+"/comment/shopComment/?repage";
	}

	@RequiresPermissions("comment:shopComment:edit")
	@RequestMapping(value = "enable")
	public String enable(ShopComment shopComment, RedirectAttributes redirectAttributes) {
		shopCommentService.enable(Integer.parseInt(shopComment.getId()));
		addMessage(redirectAttributes, "设置评论可见成功");
		return "redirect:"+Global.getAdminPath()+"/comment/shopComment/?repage";
	}

}