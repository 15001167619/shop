package com.znkf.shop.common.config;

/**
 * @author 武海升
 * @date 2018/7/4 9:50
 * description:公用常量类
 */
public class ConfigConstants {

    /**
     * 图片Api接口访问基础路径 http://192.168.1.203/shopStatic
     */
    public static String SHOP_PICTURE_BASE_PATH = ReloadableConfig.getProperty("SHOP_PICTURE_BASE_PATH", "http://192.168.1.203/shopStatic");
    /**
     * 图片存储Base路径  /usr/local/upload/shop_images
     */
    public static String UPLOAD_PICTURE_FILE_PATH = ReloadableConfig.getProperty("UPLOAD_PICTURE_FILE_PATH", "/usr/local/upload/shop_images");
    /**
     * 常识列表路径  /knowledge/list
     */
    public static String KNOWLEDGE_LIST = ReloadableConfig.getProperty("KNOWLEDGE_LIST", "/knowledge/list");
    /**
     * 常识详情页主图路径  /knowledge/primary
     */
    public static String KNOWLEDGE_PRIMARY = ReloadableConfig.getProperty("KNOWLEDGE_PRIMARY", "/knowledge/primary");
    /**
     * 用户头像路径  /user/avatar
     */
    public static String SHOP_USER_AVATAR = ReloadableConfig.getProperty("SHOP_USER_AVATAR", "/user/avatar");
    /**
     * 商品图片路径  /commodity/album
     */
    public static String COMMODITY_ALBUM = ReloadableConfig.getProperty("COMMODITY_ALBUM", "/commodity/album");
    /**
     * 商品封面图路径  /commodity/cover
     */
    public static String COMMODITY_COVER = ReloadableConfig.getProperty("COMMODITY_COVER", "/commodity/cover");
    /**
     * 商品规格图片路径  /commodity/specification
     */
    public static String COMMODITY_SPECIFICATION = ReloadableConfig.getProperty("COMMODITY_SPECIFICATION", "/commodity/specification");
    /**
     * 意见反馈图片路径  /feedback/album
     */
    public static String FEEDBACK_ALBUM = ReloadableConfig.getProperty("FEEDBACK_ALBUM", "/feedback/album");

    /**
     * 微信支付商户号 1505680651
     */
    public static String WX_MCHID = ReloadableConfig.getProperty("WX_MCHID", "1505680651");
    /**
     * 微信支付商户密钥 CqkaKaw0E0F5ZUmitaazJsRYDT8Kmk9N
     */
    public static String WX_MCHKEY = ReloadableConfig.getProperty("WX_MCHKEY", "CqkaKaw0E0F5ZUmitaazJsRYDT8Kmk9N");


    /*微信管理*/

    public static String OAUTH2_ACCESS_TOKEN_PATH = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    public static String OAUTH2_USER_INFO_PATH = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=%s";
    public static String OAUTH2_BUILD_AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect";
    public static String OAUTH2_VALIDATE_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s";
    public static String OAUTH2_REFRESH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
    public static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
    public static String WX_UNIURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


    public static String REDIRECTURL = "http://yqc9dz.natappfree.cc";

    public static class OAuth2Scope {
        public static final String SNSAPI_BASE = "snsapi_base";
        public static final String SNSAPI_USERINFO = "snsapi_userinfo";

        public OAuth2Scope() {
        }
    }

    public static String WX_APPID = ReloadableConfig.getProperty("WX_APPID", "wxe24e5480dd633333");
    public static String WX_APPSECRET = ReloadableConfig.getProperty("WX_APPSECRET", "6eed22f24152d8f95457900966c4307b");
    public static String WX_REDIRECT_URL = ReloadableConfig.getProperty("WX_REDIRECT_URL", "http://jvxrya.natappfree.cc");

}
