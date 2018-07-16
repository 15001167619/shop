package com.znkf.shop.api.wechat;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.modules.wechat.result.WxOAuth2AccessToken;
import com.znkf.shop.remote.wechat.IWechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URLEncoder;

@Controller
@WebAppConfiguration
@Slf4j
@RequestMapping(value = "${apiPath}/wechat", produces = "application/json;charset=UTF-8")
public class WechatApiController extends ApiBaseController {

    @Resource(name = "apiWechatService")
    private IWechatService wechatService;

    @ResponseBody
    @RequestMapping(value = "generateQRCode", method = RequestMethod.POST)
    public String generateQRCode(@RequestParam("timeType") Integer timeType,
                                @RequestParam("actionType") Integer actionType,
                                @RequestParam(value ="expireSeconds",required = false) String expireSeconds,
                                @RequestParam(value ="paramVale") String paramVale
    ) {
        if (timeType == null || actionType == null || paramVale == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("timeType", timeType);
        jsonObj.put("expireSeconds", expireSeconds);
        jsonObj.put("actionType", actionType);
        jsonObj.put("paramVale", paramVale);

        return wechatService.generateQRCode(jsonObj.toJSONString());
    }


    @RequestMapping(value = "authorize", method = RequestMethod.GET)
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = ConfigConstants.WX_REDIRECT_URL+"/api/wechat/getOpenId";
        // 获取微信返回的重定向url
        String redirectUrl = wechatService.oauth2buildAuthorizationUrl(url, ConfigConstants.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code，redirectUrl = {}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "getOpenId", method = RequestMethod.GET)
    public String getOpenId(@RequestParam("code") String code, @RequestParam("state") String returnUrl){
        String openId = null;
        try {
            // 使用code换取access_token信息
            WxOAuth2AccessToken wxOAuth2AccessToken = wechatService.oauth2getAccessToken(code);
            openId = wxOAuth2AccessToken.getOpenId();
        } catch (Exception e) {
            log.error("【微信网页授权】异常，{}", e);
        }

        log.info("【微信网页授权】获取openId，openId = {}", openId);
        return "redirect:" + returnUrl + "?openId=" + openId;
    }


    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public String auth(@RequestParam("code") String code) {

        log.info("进入auth方法...");
        log.info("code"+code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx6e9ee6ef047acaa7&secret=4dd7dde2ecf8b50efe21a99b64b71a03&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

        JSONObject jsonObject = JSONObject.parseObject(response);
        String openId = jsonObject.getString("openid");
        log.info("access_token"+jsonObject.getString("access_token"));
        log.info("openid"+openId);

        String returnUrl = "https://weibo.com/0926whs?topnav=1&wvr=6&topsug=1";

        return "redirect:" + returnUrl + "?openId=" + openId;
    }
}
