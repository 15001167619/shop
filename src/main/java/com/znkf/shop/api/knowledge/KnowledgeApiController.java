/**
 * Copyright &copy; 2012-2016 <a href="https://github.com.znkf.shop">JeeSite</a> All rights reserved.
 */
package com.znkf.shop.api.knowledge;


import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.knowledge.IKnowledgeService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @version 2018-07-03
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/knowledge", produces = "application/json;charset=UTF-8")
public class KnowledgeApiController extends ApiBaseController {

    @Resource(name = "apiKnowledgeService")
    private IKnowledgeService knowledgeService;

    @ResponseBody
    @RequestMapping(value = "getKnowledgeList", method = RequestMethod.GET)
    public String getKnowledgeList(@RequestParam("pageNo") Integer pageNo,
                              @RequestParam("pageSize") Integer pageSize){
        if (pageNo == null || pageSize ==null) {
            return getErrorObject();
        }
        JSONObject helpObj = new JSONObject();
        helpObj.put("pageNo", pageNo);
        helpObj.put("pageSize", pageSize);
        return knowledgeService.getKnowledgeList(helpObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "getKnowledgeDetails", method = RequestMethod.GET)
    public String getKnowledgeDetails(@RequestParam("KnowledgeId") Integer KnowledgeId){
        if (KnowledgeId == null) {
            return getErrorObject();
        }
        return knowledgeService.getKnowledgeDetails(KnowledgeId);
    }

    @ResponseBody
    @RequestMapping(value = "setKnowledgeBrowseCount", method = RequestMethod.POST)
    public String setKnowledgeBrowseCount(@RequestParam("KnowledgeId") Integer KnowledgeId){
        if (KnowledgeId == null) {
            return getErrorObject();
        }
        return knowledgeService.setKnowledgeBrowseCount(KnowledgeId);
    }

}