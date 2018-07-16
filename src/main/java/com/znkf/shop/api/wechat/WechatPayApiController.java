package com.znkf.shop.api.wechat;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.common.wechat.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author 武海升
 * @date 2018/7/11 17:32
 */
@Controller
@RequestMapping(value = "wechat")
@Slf4j
public class WechatPayApiController {

    /** 支付密钥，商户平台 > API安全 > 密钥管理 中进行设置 */
    private static final String API_KEY = ConfigConstants.WX_MCHKEY;

    /** 支付的回调方法，微信服务器调用 */
    private static final String NOTIFY_URL = "/wechat/wxPay";

    /** 获取预支付 */
    private static final String UNI_URL = ConfigConstants.WX_UNIURL;

    /** 微信公众号APPID */
    private static final String APPID = ConfigConstants.WX_APPID;

    /** 微信公众号绑定的商户号 */
    private static final String MCH_ID = ConfigConstants.WX_MCHID;

    /** 微信回调地址 */
    private static final String basePath = ConfigConstants.WX_REDIRECT_URL;


    @RequestMapping(value = "wxPay",method = RequestMethod.POST)
    public @ResponseBody JSONObject wxPay(HttpServletRequest request,
                 String commodityName,String openId, double totalPrice) throws Exception {
        /**
         * 订单金额 (分为单位)
         */
        int total = (int) (totalPrice * 100);

        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        /**
         *  公众号APPID
         */
        parameters.put("appid", APPID);
        /**
         * 商户号
         */
        parameters.put("mch_id", MCH_ID);
        /**
         * 随机字符串
         */
        parameters.put("nonce_str", WXUtil.getNonceStr());
        /**
         * 商品名称
         */
        parameters.put("body", commodityName);

        /**
         * 当前时间 yyyyMMddHHmmss
         */
        String currTime = TenpayUtil.getCurrTime();
        /**
         * 8位日期
         */
        String strTime = currTime.substring(8, currTime.length());
        /**
         * 四位随机数
         */
        String strRandom = TenpayUtil.buildRandom(4) + "";
        /**
         * 订单号
         */
        parameters.put("out_trade_no", strTime + strRandom);

        /**
         * 订单金额以分为单位，只能为整数
         */
        parameters.put("total_fee", total);
        /**
         * 客户端本地ip
         */
        parameters.put("spbill_create_ip", request.getRemoteAddr());
        /**
         * 支付回调地址
         */
        parameters.put("notify_url", basePath + NOTIFY_URL);
        /**
         * 支付方式为JSAPI支付  trade_type为JSAPI
         */
        parameters.put("trade_type", "JSAPI");
        /**
         * 用户微信的openid
         */
        parameters.put("openid", openId);

        /**
         * MD5进行签名
         */
        String sign = createSign("UTF-8", parameters);
        parameters.put("sign", sign);

        /**
         * 生成xml结构的数据，用于统一下单接口的请求
         */
        String requestXML = getRequestXml(parameters);
        log.info("requestXML：" + requestXML);
        /**
         * 获取预支付prepay_id
         */
        HttpClient client = new HttpClient();
        PostMethod myPost = new PostMethod(UNI_URL);
        client.getParams().setSoTimeout(300 * 1000);
        String result = null;
        try {
            myPost.setRequestEntity(new StringRequestEntity(requestXML, "text/xml", "utf-8"));
            int statusCode = client.executeMethod(myPost);
            if (statusCode == HttpStatus.SC_OK) {
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count = 0;
                while ((count = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                result = new String(strByte, 0, strByte.length, "utf-8");
                bos.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 关闭连接
         */
        myPost.releaseConnection();
        client.getHttpConnectionManager().closeIdleConnections(0);

        log.info("请求统一支付接口的返回结果:");
        log.info(result);
        try {
            /**
             * 解析微信返回的信息
             */
            Map<String, String> map = XMLUtil.doXMLParse(result);

            JSONObject payObj = new JSONObject();

            SortedMap<Object, Object> params = new TreeMap<Object, Object>();
            String nonceStr = WXUtil.getNonceStr();
            params.put("appId", APPID);
            params.put("timeStamp", "\"" + new Date().getTime() + "\"");
            params.put("nonceStr", nonceStr);


            payObj.put("appId", APPID);
            payObj.put("timeStamp",params.get("timeStamp"));
            payObj.put("nonceStr", nonceStr);
            /**
             * 获取预支付单号prepay_id
             * 微信支付最新接口中，要求package的值的固定格式为prepay_id=...
             */
            params.put("package", "prepay_id=" + map.get("prepay_id"));

            payObj.put("package", "prepay_id=" + map.get("prepay_id"));

            /**
             * MD5加密
             */
            params.put("signType", "MD5");

            payObj.put("signType", "MD5");
            /**
             * 获取预支付prepay_id之后，需要再次进行签名，参与签名的参数有：appId、timeStamp、nonceStr、package、signType.
             */

            log.info("【生成签名】appId，appId = {}", APPID);
            log.info("【生成签名】nonceStr，nonce_str = {}", nonceStr);
            log.info("【生成签名】package，package = {}", params.get("package"));
            log.info("【生成签名】signType，signType = {}", params.get("signType"));
            log.info("【生成签名】timeStamp，timeStamp = {}", params.get("timeStamp"));

            String paySign = createSign("UTF-8", params);
            params.put("paySign", paySign);
            payObj.put("paySign", paySign);

            /**
             * 预支付单号
             */
            payObj.put("packageValue", "prepay_id=" + map.get("prepay_id"));

            /**
             * 付款成功
             * 微信会同步请求成功通知页面
             */
            payObj.put("sendUrl", basePath + "/wechat/pay/paySuccess.html");
            /**
             * 获取用户的微信客户端版本号
             */
            String userAgent = request.getHeader("user-agent");
            char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger") + 15);
            payObj.put("agent", new String(new char[] { agent }));

            log.info("【返回微信】payObj，payObj = {}", payObj.toJSONString());

            return payObj;

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * sign签名
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            /** 如果参数为key或者sign，则不参与加密签名 */
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        /** 支付密钥必须参与加密，放在字符串最后面 */
        sb.append("key=" + API_KEY);
        log.info("【生成签名连接】 url，url = {}", sb.toString());
        /** 记得最后一定要转换为大写 */
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            String v = entry.getValue() + "";
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

}
