package com.znkf.shop.remote.order.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.modules.order.dao.ShopOrderDao;
import com.znkf.shop.modules.order.entity.ShopOrder;
import com.znkf.shop.modules.order.service.ShopOrderService;
import com.znkf.shop.remote.order.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/7/6 14:16
 */
@Service(value = "apiOrderService")
@Slf4j
public class OrderServiceImpl extends BaseService implements IOrderService {

    @Autowired
    private ShopOrderDao shopOrderDao;

    @Autowired
    private ShopOrderService shopOrderService;

    @Override
    public String addOrder(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject orderObj = JSONObject.parseObject(args);
            Integer userId = orderObj.getInteger("userId");
            Integer commodityId = orderObj.getInteger("commodityId");
            String consignee = orderObj.getString("consignee");
            String address = orderObj.getString("address");
            String mobile = orderObj.getString("mobile");
            Double orderPrice = orderObj.getDouble("orderPrice");
            Double actualPrice = orderObj.getDouble("actualPrice");
            String color = orderObj.getString("color");
            String size = orderObj.getString("size");
            String pic = orderObj.getString("pic");
            if (userId ==null || commodityId ==null || consignee ==null || address == null || mobile ==null ||
                    color ==null || size == null || pic ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            shopOrderDao.insert(new ShopOrder(userId,mobile,commodityId,orderPrice,actualPrice,consignee,address,color,size,pic));
            baseVo.setSuccessResult(220021,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String delOrder(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject orderObj = JSONObject.parseObject(args);
            Integer userId = orderObj.getInteger("userId");
            Integer orderId = orderObj.getInteger("orderId");
            if (userId ==null || orderId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            shopOrderDao.deleteOrder(userId,orderId);
            baseVo.setSuccessResult(220022,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String getOrderList(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject orderObj = JSONObject.parseObject(args);
            Integer pageNo = orderObj.getInteger("pageNo");
            Integer pageSize = orderObj.getInteger("pageSize");
            Integer userId = orderObj.getInteger("userId");
            Integer orderStatus = orderObj.getInteger("orderStatus");
            if (pageNo ==null || pageSize ==null || userId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            Page<ShopOrder> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Page<ShopOrder> listPage = shopOrderService.findOrderPage(page,new ShopOrder(userId,orderStatus));
            List<Map<String,Object>> listShopOrder = new ArrayList<>();
            for (ShopOrder ShopOrder: listPage.getList()) {
                Map<String,Object> order = new HashMap<>();
                order.put("orderId",ShopOrder.getId());
                order.put("userId", ShopOrder.getUserId());
                order.put("commodityId", ShopOrder.getCommodityId());
                order.put("orderStatus", ShopOrder.getOrderStatus());
                order.put("actualPrice", ShopOrder.getActualPrice());
                order.put("orderPrice", ShopOrder.getOrderPrice());
                order.put("color", ShopOrder.getColor());
                order.put("size", ShopOrder.getSize());
                order.put("pic", ShopOrder.getPic());
                order.put("name", ShopOrder.getName());
                order.put("brief", stripHtml(ShopOrder.getBrief()));
                listShopOrder.add(order);
            }
            JSONObject json = new JSONObject();
            json.put("listShopOrder", listShopOrder);
            json.put("sumCount", listPage.getCount());
            json.put("pages", (null == listPage || listPage.getList().size()==0) == true ? 0 : listPage.getTotalPage());
            baseVo.setSuccessResult(220029,json);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String updateOrder(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject orderObj = JSONObject.parseObject(args);
            Integer userId = orderObj.getInteger("userId");
            Integer orderId = orderObj.getInteger("orderId");
            Integer orderStatus = orderObj.getInteger("orderStatus");
            if (userId ==null || orderId ==null || orderStatus ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            shopOrderDao.updateOrder(userId,orderId,orderStatus);
            baseVo.setSuccessResult(220028,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }
}
