package com.znkf.shop.api.login;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.login.ILoginService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/11 10:44
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/auth", produces = "application/json;charset=UTF-8")
public class LoginApiController extends ApiBaseController {

    @Resource(name = "apiLoginService")
    private ILoginService loginService;

    @ResponseBody
    @RequestMapping(value = "signInOrRegister", method = RequestMethod.POST)
    public String signInOrRegister(@RequestParam("mobile") String mobile,
                                   @RequestParam("code") String code,
                                   @RequestParam("openId") String openId) {
        if (mobile == null || code == null ||openId ==null) {
            return getErrorObject();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile",mobile);
        jsonObject.put("code",code);
        jsonObject.put("openId",openId);
        return loginService.signInOrRegister(jsonObject.toJSONString());
    }

}
