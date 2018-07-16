package com.znkf.shop.api.order;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.order.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/6 14:33
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/order", produces = "application/json;charset=UTF-8")
public class OrderApiController extends ApiBaseController {

    @Resource(name = "apiOrderService")
    private IOrderService orderService;

    @ResponseBody
    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    public String addOrder(@RequestParam("userId") Integer userId,
                                 @RequestParam("commodityId") Integer commodityId,
                                 @RequestParam("consignee") String consignee,
                                 @RequestParam("mobile") String mobile,
                                 @RequestParam("color") String color,
                                 @RequestParam("size") String size,
                                 @RequestParam("pic") String pic,
                                 @RequestParam("orderPrice") Double orderPrice,
                                 @RequestParam("actualPrice") Double actualPrice,
                                 @RequestParam("address") String address
    ) {
        if (userId == null || commodityId == null ||
                consignee == null || address ==null ||mobile == null ||
                color == null || size ==null ||pic == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", userId);
        jsonObj.put("commodityId", commodityId);
        jsonObj.put("consignee", consignee);
        jsonObj.put("address", address);
        jsonObj.put("mobile", mobile);
        jsonObj.put("color", color);
        jsonObj.put("size", size);
        jsonObj.put("pic", pic);
        jsonObj.put("orderPrice", orderPrice);
        jsonObj.put("actualPrice", actualPrice);
        return orderService.addOrder(jsonObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "getOrderList", method = RequestMethod.GET)
    public String getOrderList(@RequestParam("userId") Integer userId,
                               @RequestParam("pageNo") Integer pageNo,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "orderStatus",required = false) Integer orderStatus
    ) {
        if (userId == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", userId);
        jsonObj.put("orderStatus", orderStatus);
        jsonObj.put("pageNo", pageNo);
        jsonObj.put("pageSize", pageSize);
        return orderService.getOrderList(jsonObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "delOrder", method = RequestMethod.POST)
    public String delOrder(@RequestParam("userId") Integer userId,
                                 @RequestParam("orderId") Integer orderId
    ) {
        if (userId == null || orderId == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", userId);
        jsonObj.put("orderId", orderId);
        return orderService.delOrder(jsonObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "updateOrder", method = RequestMethod.POST)
    public String updateOrder(@RequestParam("userId") Integer userId,
                                 @RequestParam("orderId") Integer orderId,
                                 @RequestParam("orderStatus") Integer orderStatus
    ) {
        if (userId == null || orderId == null || orderStatus ==null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", userId);
        jsonObj.put("orderId", orderId);
        jsonObj.put("orderStatus", orderStatus);
        return orderService.updateOrder(jsonObj.toJSONString());
    }

}
