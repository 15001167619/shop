package com.znkf.shop.remote.coupon.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.modules.relation.entity.ShopUserCoupon;
import com.znkf.shop.modules.relation.service.ShopUserCouponService;
import com.znkf.shop.remote.coupon.ICouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/7/10 14:41
 */
@Service(value = "apiCouponService")
@Slf4j
public class CouponServiceImpl extends BaseService implements ICouponService {
    @Autowired
    private ShopUserCouponService userCouponService;

    @Override
    public String getCouponList(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject couponObj = JSONObject.parseObject(args);
            Integer pageNo = couponObj.getInteger("pageNo");
            Integer pageSize = couponObj.getInteger("pageSize");
            Integer userId = couponObj.getInteger("userId");
            if (pageNo ==null || pageSize ==null || userId == null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            Page<ShopUserCoupon> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Page<ShopUserCoupon> listPage = userCouponService.findCouponPage(page,new ShopUserCoupon(userId));
            List<Map<String,Object>> listCoupon = new ArrayList<>();
            for (ShopUserCoupon coupon: listPage.getList()) {
                Map<String,Object> map = new HashMap<>();
                map.put("couponId",coupon.getCouponId());
                map.put("couponName",coupon.getCouponName());
                map.put("money",stripHtml(coupon.getMoney()));
                listCoupon.add(map);
            }
            JSONObject json = new JSONObject();
            json.put("listCoupon", listCoupon);
            json.put("sumCount", listPage.getCount());
            json.put("pages", (null == listPage || listPage.getList().size()==0) == true ? 0 : listPage.getTotalPage());
            baseVo.setSuccessResult(220002,json);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }
}
