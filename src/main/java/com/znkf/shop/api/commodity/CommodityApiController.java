package com.znkf.shop.api.commodity;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.commodity.ICommodityService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/6 10:56
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/commodity", produces = "application/json;charset=UTF-8")
public class CommodityApiController extends ApiBaseController {

    @Resource(name = "apiCommodityService")
    private ICommodityService commodityService;

    @ResponseBody
    @RequestMapping(value = "getCommodityList", method = RequestMethod.GET)
    public String getCommodityList(@RequestParam(value = "isHot",required = false) Integer isHot,
                                   @RequestParam("pageNo") Integer pageNo,
                                   @RequestParam("pageSize") Integer pageSize){
        if (pageNo == null || pageSize ==null) {
            return getErrorObject();
        }
        JSONObject commodityObj = new JSONObject();
        commodityObj.put("pageNo", pageNo);
        commodityObj.put("pageSize", pageSize);
        commodityObj.put("isHot", isHot);
        return commodityService.getCommodityList(commodityObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "getCommodityDetails", method = RequestMethod.GET)
    public String getCommodityDetails(@RequestParam("commodityId") Integer commodityId){
        if (commodityId == null) {
            return getErrorObject();
        }
        return commodityService.getCommodityDetails(commodityId);
    }

}
