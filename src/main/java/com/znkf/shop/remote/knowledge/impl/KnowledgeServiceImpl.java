package com.znkf.shop.remote.knowledge.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.modules.knowledge.dao.ShopKnowledgeDao;
import com.znkf.shop.modules.knowledge.entity.ShopKnowledge;
import com.znkf.shop.modules.knowledge.service.ShopKnowledgeService;
import com.znkf.shop.remote.knowledge.IKnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/7/3 18:16
 */
@Service(value = "apiKnowledgeService")
@Slf4j
public class KnowledgeServiceImpl extends BaseService implements IKnowledgeService {

    @Autowired
    private ShopKnowledgeDao shopKnowledgeDao;

    @Autowired
    private ShopKnowledgeService shopKnowledgeService;

    @Override
    public String getKnowledgeList(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject knowledgeObj = JSONObject.parseObject(args);
            Integer pageNo = knowledgeObj.getInteger("pageNo");
            Integer pageSize = knowledgeObj.getInteger("pageSize");
            if (pageNo ==null || pageSize ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            Page<ShopKnowledge> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Page<ShopKnowledge> listPage = shopKnowledgeService.findPage(page,new ShopKnowledge());
            List<Map<String,Object>> listShopKnowledge = new ArrayList<>();
            for (ShopKnowledge shopKnowledge: listPage.getList()) {
                Map<String,Object> knowledge = new HashMap<>();
                knowledge.put("knowledgeId",shopKnowledge.getId());
                knowledge.put("name", shopKnowledge.getName());
                knowledge.put("desc", stripHtml(shopKnowledge.getKnowledgeDesc()));
                knowledge.put("listPicUrl", ConfigConstants.SHOP_PICTURE_BASE_PATH+shopKnowledge.getListPicUrl());
                knowledge.put("primaryPicUrl",ConfigConstants.SHOP_PICTURE_BASE_PATH+shopKnowledge.getPrimaryPicUrl());
                knowledge.put("browseCount", shopKnowledge.getBrowseCount());
                listShopKnowledge.add(knowledge);
            }
            JSONObject json = new JSONObject();
            json.put("listHelp", listShopKnowledge);
            json.put("sumCount", listPage.getCount());
            json.put("pages", (null == listPage || listPage.getList().size()==0) == true ? 0 : listPage.getTotalPage());
            baseVo.setSuccessResult(220005,json);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String getKnowledgeDetails(Integer KnowledgeId) {
        BaseVo baseVo = new BaseVo();
        try {
            if (KnowledgeId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            ShopKnowledge shopKnowledge = shopKnowledgeDao.get(String.valueOf(KnowledgeId));
            if(shopKnowledge==null){
                baseVo.setResult(220006);
                return this.getJsonString(baseVo);
            }
            JSONObject knowledge = new JSONObject();
            knowledge.put("knowledgeId",shopKnowledge.getId());
            knowledge.put("name", shopKnowledge.getName());
            knowledge.put("desc", stripHtml(shopKnowledge.getKnowledgeDesc()));
            knowledge.put("listPicUrl", ConfigConstants.SHOP_PICTURE_BASE_PATH+shopKnowledge.getListPicUrl());
            knowledge.put("primaryPicUrl",ConfigConstants.SHOP_PICTURE_BASE_PATH+shopKnowledge.getPrimaryPicUrl());
            knowledge.put("browseCount", shopKnowledge.getBrowseCount());
            baseVo.setSuccessResult(220007,knowledge);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String setKnowledgeBrowseCount(Integer KnowledgeId) {
        BaseVo baseVo = new BaseVo();
        try {
            if (KnowledgeId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            shopKnowledgeDao.setKnowledgeBrowseCount(KnowledgeId);
            baseVo.setSuccessResult(220027,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }
}
