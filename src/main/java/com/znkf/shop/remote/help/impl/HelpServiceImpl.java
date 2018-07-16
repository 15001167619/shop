package com.znkf.shop.remote.help.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.modules.help.dao.ShopHelpDao;
import com.znkf.shop.modules.help.entity.ShopHelp;
import com.znkf.shop.modules.help.service.ShopHelpService;
import com.znkf.shop.remote.help.IHelpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/7/3 18:19
 */
@Service(value = "apiHelpService")
@Slf4j
public class HelpServiceImpl extends BaseService implements IHelpService {

    @Autowired
    private ShopHelpDao shopHelpDao;

    @Autowired
    private ShopHelpService shopHelpService;

    @Override
    public String getHelpList(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject helpObj = JSONObject.parseObject(args);
            Integer pageNo = helpObj.getInteger("pageNo");
            Integer pageSize = helpObj.getInteger("pageSize");
            if (pageNo ==null || pageSize ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            Page<ShopHelp> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Page<ShopHelp> listPage = shopHelpService.findPage(page,new ShopHelp());
            List<Map<String,Object>> listHelp = new ArrayList<>();
            for (ShopHelp help: listPage.getList()) {
                Map<String,Object> map = new HashMap<>();
                map.put("helpId",help.getId());
                map.put("question",stripHtml(help.getQuestion()));
                map.put("solution",stripHtml(help.getSolution()));
                listHelp.add(map);
            }
            JSONObject json = new JSONObject();
            json.put("listHelp", listHelp);
            json.put("sumCount", listPage.getCount());
            json.put("pages", (null == listPage || listPage.getList().size()==0) == true ? 0 : listPage.getTotalPage());
            baseVo.setSuccessResult(220002,json);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String getHelpDetails(Integer helpId) {
        BaseVo baseVo = new BaseVo();
        try {
            if (helpId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            ShopHelp shopHelp = shopHelpDao.get(String.valueOf(helpId));
            if(shopHelp==null){
                baseVo.setResult(220003);
                return this.getJsonString(baseVo);
            }
            JSONObject helpInfo = new JSONObject();
            helpInfo.put("helpId",shopHelp.getId());
            helpInfo.put("question", shopHelp.getQuestion());
            helpInfo.put("solution", shopHelp.getSolution());
            baseVo.setSuccessResult(220004,helpInfo);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }
}
