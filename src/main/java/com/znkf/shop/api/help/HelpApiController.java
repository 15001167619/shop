/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.api.help;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.help.IHelpService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 帮助中心Controller
 * @author 武海升
 * @version 2018-07-02
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/help", produces = "application/json;charset=UTF-8")
public class HelpApiController extends ApiBaseController {

	@Resource(name = "apiHelpService")
	private IHelpService shopHelpService;

	@ResponseBody
	@RequestMapping(value = "getHelpList", method = RequestMethod.GET)
	public String getHelpList(@RequestParam("pageNo") Integer pageNo,
								@RequestParam("pageSize") Integer pageSize){
		if (pageNo == null || pageSize ==null) {
			return getErrorObject();
		}
		JSONObject helpObj = new JSONObject();
		helpObj.put("pageNo", pageNo);
		helpObj.put("pageSize", pageSize);
		return shopHelpService.getHelpList(helpObj.toJSONString());
	}

	@ResponseBody
	@RequestMapping(value = "getHelpDetails", method = RequestMethod.GET)
	public String getHelpDetails(@RequestParam("helpId") Integer helpId){
		if (helpId == null) {
			return getErrorObject();
		}
		return shopHelpService.getHelpDetails(helpId);
	}
	


}