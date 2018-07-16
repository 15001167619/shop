package com.znkf.shop.common.wechat;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.config.ReloadableConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 武海升
 * @version 2.0
 * @description
 * @date 2018-02-05 17:52
 */
public class AccessTokenUtils {

    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String SECRET = ReloadableConfig.getProperty("WX_APPSECRET", "6eed22f24152d8f95457900966c4307b");
    private static HttpClient client;

    public static String getToken(String appid) {
        String turl = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", GET_TOKEN_URL, appid, SECRET);
        client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            JSONObject jsonObject =  JSONObject.parseObject(responseContent);
            client.getConnectionManager().shutdown();
            return jsonObject.getString("access_token")==null?null:jsonObject.getString("access_token");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
