package com.znkf.shop.remote.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.common.config.ReloadableConfig;
import com.znkf.shop.common.utils.HttpClientUtil;
import com.znkf.shop.common.wechat.AccessTokenUtils;
import com.znkf.shop.common.wechat.HttpsUtil;
import com.znkf.shop.modules.wechat.result.WxOAuth2AccessToken;
import com.znkf.shop.remote.wechat.IWechatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 武海升
 * @date 2018/7/3 14:15
 */
@Service(value = "apiWechatService")
@Slf4j
public class WechatServiceImpl extends BaseService implements IWechatService {

    @Override
    public String generateQRCode(String args) {
        log.info("调用微信二维码接口");
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject wxObj = JSONObject.parseObject(args);
            Integer timeType = wxObj.getInteger("timeType");//二维码时效类型
            Integer actionType = wxObj.getInteger("actionType");//actionType 二维码参数类型
            Integer expireSeconds = wxObj.getInteger("expireSeconds");
            String paramVale = wxObj.getString("paramVale");
            if (timeType ==null || actionType ==null || paramVale ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }

            JSONObject codeParams = new JSONObject();

            JSONObject scene_params = new JSONObject();

            String action_name;

            switch (timeType) {
                case 0://临时二维码
                    codeParams.put("expire_seconds",expireSeconds==null?30:expireSeconds);

                    switch (actionType) {
                        case 0://0 整数类型
                            action_name = "QR_SCENE";
                            scene_params.put("scene_id",paramVale);
                            break;
                        case 1://1 字符串类型
                            action_name = "QR_STR_SCENE";
                            scene_params.put("scene_str",paramVale);
                            break;
                        default:
                            baseVo.setResult(100001);
                            return this.getJsonString(baseVo);
                    }
                    break;
                case 1://永久二维码
                    switch (actionType) {
                        case 0://0 整数类型
                            action_name = "QR_LIMIT_SCENE";
                            scene_params.put("scene_id",paramVale);
                            break;
                        case 1://1 字符串类型
                            action_name = "QR_LIMIT_STR_SCENE";
                            scene_params.put("scene_str",paramVale);
                            break;
                        default:
                            baseVo.setResult(100001);
                            return this.getJsonString(baseVo);
                    }
                    break;
                default:
                    baseVo.setResult(100001);
                    return this.getJsonString(baseVo);
            }

            JSONObject action_info_params = new JSONObject();
            action_info_params.put("scene",scene_params);
            codeParams.put("action_name",action_name);
            codeParams.put("action_info",action_info_params);
            String params = codeParams.toJSONString();
            String appid = ReloadableConfig.getProperty("WX_APPID", "wxe24e5480dd633333");
            String wx_AccessToken = AccessTokenUtils.getToken(appid);
            String sUrl ="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
            sUrl = sUrl.replace("TOKEN", wx_AccessToken);
            String ticket = HttpsUtil.httpsRequest(sUrl, "POST", params).getString("ticket");
            String basePath = ReloadableConfig.getProperty("WX_CODEPATH", "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=");;
            String codePath = basePath+ticket;
            baseVo.setSuccessResult(200002,codePath);
            return this.getJsonString(baseVo);

        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String oauth2buildAuthorizationUrl(String redirectURI, String scope, String state) {
        return String.format(ConfigConstants.OAUTH2_BUILD_AUTHORIZATION_URL, new Object[]{ConfigConstants.WX_APPID, HttpClientUtil.encodeURIComponent(redirectURI), scope, StringUtils.trimToEmpty(state)});
    }

    @Override
    public WxOAuth2AccessToken oauth2getAccessToken(String code) {
        String url = String.format(ConfigConstants.OAUTH2_ACCESS_TOKEN_PATH, new Object[]{ConfigConstants.WX_APPID, ConfigConstants.WX_APPSECRET, code});
        return this.getOAuth2AccessToken(url);
    }

    private WxOAuth2AccessToken getOAuth2AccessToken(String url){
        try {
            String responseText = HttpClientUtil.get(url);
            return WxOAuth2AccessToken.fromJson(responseText);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
