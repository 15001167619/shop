package com.znkf.shop.remote.wechat;

import com.znkf.shop.modules.wechat.result.WxOAuth2AccessToken;

/**
 * @author 武海升
 * @date 2018/7/3 14:14
 */
public interface IWechatService {

    /**
     * @author 武海升
     * @desc 生成二维码
     * @param timeType 二维码时效类型  0 临时二维码 1 永久二维码
     * @param actionType actionType    0 整数类型 1 字符串类型
     * @param expireSeconds 二维码有效时间 以秒为单位 临时二维码默认有效期为30s 永久二维码可不设置
     * @param paramVale 参数值
     */
    String generateQRCode(String args);

    String oauth2buildAuthorizationUrl(String redirectURI, String scope, String state);

    WxOAuth2AccessToken oauth2getAccessToken(String code);



}
