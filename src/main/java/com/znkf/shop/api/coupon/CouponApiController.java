package com.znkf.shop.api.coupon;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.coupon.ICouponService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/10 14:47
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/coupon", produces = "application/json;charset=UTF-8")
public class CouponApiController extends ApiBaseController {

    @Resource(name = "apiCouponService")
    private ICouponService couponService;

    @ResponseBody
    @RequestMapping(value = "getCouponList", method = RequestMethod.GET)
    public String getCouponList(@RequestParam("pageNo") Integer pageNo,
                                   @RequestParam("userId") Integer userId,
                                   @RequestParam("pageSize") Integer pageSize){
        if (pageNo == null || pageSize ==null ||userId == null) {
            return getErrorObject();
        }
        JSONObject helpObj = new JSONObject();
        helpObj.put("pageNo", pageNo);
        helpObj.put("pageSize", pageSize);
        helpObj.put("userId", userId);
        return couponService.getCouponList(helpObj.toJSONString());
    }


}
