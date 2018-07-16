package com.znkf.shop.common.wechat;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.modules.wechat.result.PayToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 武海升
 * @date 2018/7/12 8:48
 */
public class PayTokenUtils {

    private static PayToken payToken;
    private static HttpClient client;

    public PayTokenUtils() {
    }


    public static PayToken getAccessToken() throws Exception {
        long currentTime = System.currentTimeMillis();
        if (payToken == null || currentTime - payToken.getTokenCreateTime() >= 7200000L) {
            payToken = new PayToken();
            String appid = ConfigConstants.WX_APPID;
            String secret = ConfigConstants.WX_APPSECRET;
            String requestUrl = ConfigConstants.GET_ACCESS_TOKEN_URL.replace("APPID", appid).replace("SECRET", secret);

            client = new DefaultHttpClient();
            HttpGet get = new HttpGet(requestUrl);
            String result = null;
            try {
                HttpResponse res = client.execute(get);
                String responseContent = null; // 响应内容
                HttpEntity entity = res.getEntity();
                responseContent = EntityUtils.toString(entity, "UTF-8");
                JSONObject jsonObject =  JSONObject.parseObject(responseContent);
                client.getConnectionManager().shutdown();
                if (null != jsonObject) {
                    String accessToken = jsonObject.getString("access_token");
                    int expiresIn = jsonObject.getInteger("expires_in");
                    payToken.setAccessToken(accessToken);
                    payToken.setExpiresIn(expiresIn);
                    payToken.setTokenCreateTime(currentTime);
                } else {
                    payToken = null;
                }
                return payToken;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        return payToken;
    }
}
