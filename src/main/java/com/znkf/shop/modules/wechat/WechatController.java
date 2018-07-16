package com.znkf.shop.modules.wechat;

import com.znkf.shop.common.config.ReloadableConfig;
import com.znkf.shop.common.wechat.AccessTokenUtils;
import com.znkf.shop.common.wechat.HttpsUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信管理Controller
 * @author 武海升
 * @version 2018-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/wechat/function")
public class WechatController{

    @RequestMapping(value = "generateQRCode")
    public String generateQRCode() {
        return "modules/wechat/generateQRCodeForm";
    }

    @RequestMapping(value = "setGenerateQRCode")
    @ResponseBody
    public String setGenerateQRCode(String param) {
        param = StringEscapeUtils.unescapeHtml4(param);
        String appid = ReloadableConfig.getProperty("WX_APPID", "wxe24e5480dd633333");
        String wx_AccessToken = AccessTokenUtils.getToken(appid);
        String sUrl ="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
        sUrl = sUrl.replace("TOKEN", wx_AccessToken);
        return HttpsUtil.httpsRequest(sUrl, "POST", param).getString("ticket");
    }

}
