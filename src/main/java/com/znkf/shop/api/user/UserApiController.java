package com.znkf.shop.api.user;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.user.IShopUserService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/5 14:04
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/user", produces = "application/json;charset=UTF-8")
public class UserApiController extends ApiBaseController {

    @Resource(name = "apiShopUserService")
    private IShopUserService shopUserService;

    @ResponseBody
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public String getUserInfo(@RequestParam("userId") Integer userId) {
        if (userId == null) {
            return getErrorObject();
        }
        return shopUserService.getUserInfo(userId);
    }

    @ResponseBody
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(@RequestParam("userId") Integer userId,
                                @RequestParam("gender") Integer gender,
                                @RequestParam("userName") String userName,
                                @RequestParam("birthday") String birthday
    ) {
        if (userId == null || gender == null || userName == null || birthday ==null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", userId);
        jsonObj.put("gender", gender);
        jsonObj.put("userName", userName);
        jsonObj.put("birthday", birthday);

        return shopUserService.updateUserInfo(jsonObj.toJSONString());
    }


}
