package com.znkf.shop.remote.commodity.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.modules.commodity.dao.ShopCommodityDao;
import com.znkf.shop.modules.commodity.dao.ShopSpecificationDao;
import com.znkf.shop.modules.commodity.entity.ShopCommodity;
import com.znkf.shop.modules.commodity.entity.ShopSpecification;
import com.znkf.shop.modules.commodity.service.ShopCommodityService;
import com.znkf.shop.remote.commodity.ICommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/7/6 10:31
 */
@Service(value = "apiCommodityService")
@Slf4j
public class CommodityServiceImpl extends BaseService implements ICommodityService {

    @Autowired
    private ShopCommodityService shopCommodityService;

    @Autowired
    private ShopCommodityDao shopCommodityDao;

    @Autowired
    private ShopSpecificationDao shopSpecificationDao;

    @Override
    public String getCommodityList(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject commodityObj = JSONObject.parseObject(args);
            Integer pageNo = commodityObj.getInteger("pageNo");
            Integer pageSize = commodityObj.getInteger("pageSize");
            Integer isOnSale = commodityObj.getInteger("isHot");
            if (pageNo ==null || pageSize ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            Page<ShopCommodity> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Page<ShopCommodity> listPage = shopCommodityService.findPage(page,new ShopCommodity(isOnSale));
            List<Map<String,Object>> listCommodity = new ArrayList<>();
            for (ShopCommodity shopCommodity: listPage.getList()) {
                Map<String,Object> commodity = new HashMap<>();
                commodity.put("commodityId",shopCommodity.getId());
                commodity.put("name",shopCommodity.getName());
                commodity.put("englishName",shopCommodity.getEnglishName());
                commodity.put("brief",stripHtml(shopCommodity.getBrief()));
                commodity.put("coverPicUrl",ConfigConstants.SHOP_PICTURE_BASE_PATH+shopCommodity.getListPicUrl());
                listCommodity.add(commodity);
            }
            JSONObject json = new JSONObject();
            json.put("listCommodity", listCommodity);
            json.put("sumCount", listPage.getCount());
            json.put("pages", (null == listPage || listPage.getList().size()==0) == true ? 0 : listPage.getTotalPage());
            baseVo.setSuccessResult(220016,json);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String getCommodityDetails(Integer commodityId) {
        BaseVo baseVo = new BaseVo();
        try {
            if (commodityId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            ShopCommodity shopCommodity = shopCommodityDao.get(String.valueOf(commodityId));
            if(shopCommodity == null){
                baseVo.setResult(220018);
                return this.getJsonString(baseVo);
            }
            JSONObject commodityInfo = new JSONObject();
            commodityInfo.put("commodityId",shopCommodity.getId());
            commodityInfo.put("name",shopCommodity.getName());
            commodityInfo.put("brief",stripHtml(shopCommodity.getBrief()));
            commodityInfo.put("isOnSale",shopCommodity.getIsOnSale());
            commodityInfo.put("counterPrice",shopCommodity.getIsOnSale());
            commodityInfo.put("listPicUrl",ConfigConstants.SHOP_PICTURE_BASE_PATH+shopCommodity.getListPicUrl());
            commodityInfo.put("unit",shopCommodity.getUnit());
            commodityInfo.put("retailPrice",shopCommodity.getRetailPrice());
            commodityInfo.put("description",shopCommodity.getDescription());
            JSONObject resultObj = new JSONObject();
            resultObj.put("commodityInfo",commodityInfo);
            List<ShopSpecification> list = shopSpecificationDao.findList(new ShopSpecification());
            List<Map<String,Object>> mapList = new ArrayList<>();
            for (ShopSpecification shopSpecification:list
                 ) {
                Map<String,Object> map = new HashMap<>();
                map.put("specificationId",shopSpecification.getId());
                map.put("color",shopSpecification.getColor());
                map.put("size",shopSpecification.getSize());
                map.put("picUrl",ConfigConstants.SHOP_PICTURE_BASE_PATH+shopSpecification.getPicUrl());
                mapList.add(map);
            }
            resultObj.put("specificationInfo",mapList);
            baseVo.setSuccessResult(220017,resultObj);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

}
