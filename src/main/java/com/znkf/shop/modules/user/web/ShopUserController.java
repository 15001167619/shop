/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.user.web;

import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.sys.service.SystemService;
import com.znkf.shop.modules.user.entity.ShopUser;
import com.znkf.shop.modules.user.service.ShopUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 会员管理Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/user/shopUser")
public class ShopUserController extends BaseController {

	@Autowired
	private ShopUserService shopUserService;
	
	@ModelAttribute
	public ShopUser get(@RequestParam(required=false) String id) {
		ShopUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopUserService.get(id);
		}
		if (entity == null){
			entity = new ShopUser();
		}
		return entity;
	}
	
	@RequiresPermissions("user:shopUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopUser shopUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopUser> page = shopUserService.findPage(new Page<ShopUser>(request, response), shopUser); 
		model.addAttribute("page", page);
		return "modules/user/shopUserList";
	}

	@RequiresPermissions("user:shopUser:view")
	@RequestMapping(value = "form")
	public String form(ShopUser shopUser, Model model) {
	    if(StringUtils.isBlank(shopUser.getId())){
            shopUser.setAddTime(new Date());
            shopUser.setStatus(0);
            shopUser.setDeleted(0);
            shopUser.setUserLevel(0);
            shopUser.setGender(1);
        }
		model.addAttribute("shopUser", shopUser);
		return "modules/user/shopUserForm";
	}

	@RequiresPermissions("user:shopUser:edit")
	@RequestMapping(value = "save")
	public String save(ShopUser shopUser, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotBlank(shopUser.getPassword())) {
			shopUser.setPassword(SystemService.entryptPassword(shopUser.getPassword()));
		}
		shopUserService.save(shopUser);
		addMessage(redirectAttributes, "保存会员成功");
		return "redirect:"+Global.getAdminPath()+"/user/shopUser/?repage";
	}
	
	@RequiresPermissions("user:shopUser:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopUser shopUser, RedirectAttributes redirectAttributes) {
		shopUserService.delete(shopUser);
		addMessage(redirectAttributes, "删除会员成功");
		return "redirect:"+Global.getAdminPath()+"/user/shopUser/?repage";
	}

	@RequestMapping(value = "isMobileRepeat")
	@ResponseBody
	public Boolean isMobileRepeat(String mobile) {
		if(mobile==null){
			return false;
		}
		try {
			Integer count = shopUserService.getMobile(mobile.trim());
			return count==1?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



}