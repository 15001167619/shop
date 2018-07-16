/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.modules.picture.web;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.common.utils.FileUtils;
import com.znkf.shop.common.web.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片Controller
 * @author 武海升
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/picture")
@Slf4j
public class ShopPictureController extends BaseController {



	/**
	 * @author 武海升
	 * @desc 上传常识列表图
	 * @param multipartFile 文件
	 */
	@RequestMapping(value = "listUrlPicUpload")
	@ResponseBody
	public String listUrlPicUpload(MultipartFile file) {
		JSONObject jsonObject = FileUtils.setFilenamePath(file, ConfigConstants.KNOWLEDGE_LIST);
		//上传图片
		FileUtils.uploadFile(file,jsonObject.getString("filenamePath"));
		return jsonObject.getString("returnFilenamePath");
	}

	/**
	 * @author 武海升
	 * @desc 上传常识主图
	 * @param multipartFile 文件
	 */
	@RequestMapping(value = "primaryUrlUpload")
	@ResponseBody
	public String primaryUrlUpload(MultipartFile file) {
		JSONObject jsonObject = FileUtils.setFilenamePath(file, ConfigConstants.KNOWLEDGE_PRIMARY);
		//上传图片
		FileUtils.uploadFile(file,jsonObject.getString("filenamePath"));
		return jsonObject.getString("returnFilenamePath");
	}

	/**
	 * @author 武海升
	 * @desc 上传用户头像
	 * @param multipartFile 文件
	 */
	@RequestMapping(value = "avatarUrlPicUpload")
	@ResponseBody
	public String avatarUrlPicUpload(MultipartFile file) {
		JSONObject jsonObject = FileUtils.setFilenamePath(file, ConfigConstants.SHOP_USER_AVATAR);
		//上传图片
		FileUtils.uploadFile(file,jsonObject.getString("filenamePath"));
		return jsonObject.getString("returnFilenamePath");
	}

	/**
	 * @author 武海升
	 * @desc 上传商品图片
	 * @param MultipartFiles 文件集
	 */
	@RequestMapping(value = "commodityUrlPicUpload")
	@ResponseBody
	public String commodityUrlPicUpload(@RequestParam(value = "imgFile", required = false) MultipartFile[] files) {
		MultipartFile file = null;
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				file = files[i];
			}
		}
		JSONObject jsonObject = FileUtils.setFilenamePath(file, ConfigConstants.COMMODITY_ALBUM);
		//上传图片
		FileUtils.uploadFile(file,jsonObject.getString("filenamePath"));
		return jsonObject.getString("returnFilenamePath");
	}

	/**
	 * @author 武海升
	 * @desc 上传商品封面图
	 * @param multipartFile 文件
	 */
	@RequestMapping(value = "commodityCoverUpload")
	@ResponseBody
	public String commodityCoverUpload(MultipartFile file) {
		JSONObject jsonObject = FileUtils.setFilenamePath(file, ConfigConstants.COMMODITY_COVER);
		//上传图片
		FileUtils.uploadFile(file,jsonObject.getString("filenamePath"));
		return jsonObject.getString("returnFilenamePath");
	}

	/**
	 * @author 武海升
	 * @desc 上传商品规格图
	 * @param multipartFile 文件
	 */
	@RequestMapping(value = "commoditySpecificationUpload")
	@ResponseBody
	public String commoditySpecificationUpload(MultipartFile file) {
		JSONObject jsonObject = FileUtils.setFilenamePath(file, ConfigConstants.COMMODITY_SPECIFICATION);
		//上传图片
		FileUtils.uploadFile(file,jsonObject.getString("filenamePath"));
		return jsonObject.getString("returnFilenamePath");
	}
}