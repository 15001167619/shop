package com.znkf.shop.remote.login;

/**
 * @author 武海升
 * @date 2018/7/11 10:48
 */
public interface ILoginService {

    /**
     * @author 武海升
     * @desc 用户登录或注册
     * @param mobile 手机号
     * @param code 短信验证码
     * @param weixinOpenid 微信OpenId
     */
    String signInOrRegister(String args);

}
