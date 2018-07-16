/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.commodity.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.config.Global;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.common.web.BaseController;
import com.znkf.shop.modules.commodity.entity.ShopCommodity;
import com.znkf.shop.modules.commodity.entity.ShopSpecification;
import com.znkf.shop.modules.commodity.service.ShopCommodityService;
import com.znkf.shop.modules.commodity.service.ShopSpecificationService;
import com.znkf.shop.modules.picture.entity.ShopPicture;
import com.znkf.shop.modules.picture.service.ShopPictureService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/commodity/shopCommodity")
public class ShopCommodityController extends BaseController {

	@Autowired
	private ShopCommodityService shopCommodityService;

	@Autowired
	private ShopPictureService shopPictureService;

	@Autowired
	private ShopSpecificationService shopSpecificationService;

	@ModelAttribute
	public ShopCommodity get(@RequestParam(required=false) String id) {
		ShopCommodity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopCommodityService.get(id);
		}
		if (entity == null){
			entity = new ShopCommodity();
		}
		return entity;
	}
	
	@RequiresPermissions("commodity:shopCommodity:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopCommodity shopCommodity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopCommodity> page = shopCommodityService.findPage(new Page<ShopCommodity>(request, response), shopCommodity); 
		model.addAttribute("page", page);
		return "modules/commodity/shopCommodityList";
	}

	@RequiresPermissions("commodity:shopCommodity:view")
	@RequestMapping(value = "form")
	public String form(ShopCommodity shopCommodity, Model model) {
		if(StringUtils.isBlank(shopCommodity.getId())){
			shopCommodity.setIsOnSale(1);//初始化设置商品在售
		}else{
			//获取商品图片
			List<ShopPicture> shopPictureList = shopPictureService.findList(new ShopPicture(Integer.parseInt(shopCommodity.getId()), 0, 1));
			String pictureAlbumString = "";
			List<String> pictureAlbumList = new ArrayList<String>();
			for (ShopPicture pictureAlbum : shopPictureList) {
				if (pictureAlbum.getPath() == null || pictureAlbum.getPath().length() == 0)continue;
				pictureAlbumString = pictureAlbumString + pictureAlbum.getPath() + ";";
				pictureAlbumList.add(pictureAlbum.getPath());
			}
			if (pictureAlbumString.length() > 0) pictureAlbumString = pictureAlbumString.substring(0, pictureAlbumString.length() - 1);
			model.addAttribute("projectAlbum", pictureAlbumList);
			model.addAttribute("pictureAlbumString", pictureAlbumString);
			//获取规格列表
			List<ShopSpecification> list = shopSpecificationService.findList(new ShopSpecification(Integer.parseInt(shopCommodity.getId())));
			List<Map<String,Object>> resultList = new ArrayList<>();
			for (ShopSpecification shopSpecification : list) {
				Map<String,Object> map = new HashMap<>();
				map.put("color",shopSpecification.getColor());
				map.put("size",shopSpecification.getSize());
				if(shopSpecification.getPicUrl()==null||"".equals(shopSpecification.getPicUrl().trim())){
					map.put("picUrl","");
				}else{
					map.put("picUrl",shopSpecification.getPicUrl());
				}
				resultList.add(map);
			}
			model.addAttribute("specificationList", resultList);
		}
		model.addAttribute("shopCommodity", shopCommodity);
		return "modules/commodity/shopCommodityForm";
	}

	@RequiresPermissions("commodity:shopCommodity:edit")
	@RequestMapping(value = "save")
	public String save(ShopCommodity shopCommodity, String originalPicUrl,
					   String specifications,RedirectAttributes redirectAttributes) {
		shopCommodity.setBrief(StringEscapeUtils.unescapeHtml4(shopCommodity.getBrief()));
		shopCommodity.setDescription(StringEscapeUtils.unescapeHtml4(shopCommodity.getDescription()));
		shopCommodityService.save(shopCommodity);
		//保存商品图片
		setOriginalPicUrl(originalPicUrl,shopCommodity.getId());
		//保存商品规格
		specifications = StringEscapeUtils.unescapeHtml4(specifications);
		setSpecification(specifications,shopCommodity.getId());

		addMessage(redirectAttributes, "保存商品成功");

		return "redirect:"+Global.getAdminPath()+"/commodity/shopCommodity/?repage";
	}

	private void setSpecification(String specifications, String id) {

		shopSpecificationService.deleteAllByCommodityId(Integer.parseInt(id));

		JSONArray specificationArray = JSONObject.parseArray(specifications);
		if(specificationArray!=null && specificationArray.size()>0){
			System.out.println(specificationArray);
			for (Object obj: specificationArray
				 ) {
				JSONObject jsonObject = (JSONObject)obj;
				if(jsonObject==null)continue;
				shopSpecificationService.save(new ShopSpecification(Integer.parseInt(id),jsonObject.getString("color"),jsonObject.getInteger("size"),jsonObject.getString("picUrl")));
			}
		}
	}

	private void setOriginalPicUrl(String originalPicUrl,String id){

		shopPictureService.deleteAllPicture(new ShopPicture(Integer.parseInt(id),0,1));
		if (originalPicUrl != null && originalPicUrl.length() > 0) {
			String[] picUrlArray = originalPicUrl.split(";");
			for(String picUrl : picUrlArray){
				if(picUrl==null||picUrl.length()==0)continue;
				shopPictureService.save(new ShopPicture(Integer.parseInt(id),0,1,picUrl));
			}
		}

	}

	@RequiresPermissions("commodity:shopCommodity:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopCommodity shopCommodity, RedirectAttributes redirectAttributes) {
		shopCommodityService.delete(shopCommodity);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:"+Global.getAdminPath()+"/commodity/shopCommodity/?repage";
	}

}