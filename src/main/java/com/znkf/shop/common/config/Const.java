package com.znkf.shop.common.config;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author JeffOsmond
 * @version V1.0
 * @package com.zkhc.znkf.common.config
 * @description 项目配置参数类
 * @email yinjiaxing_web@163.com
 * create time 2018/1/27
 */
public class Const {

    //手机验证码拼接标识
    public static final String REDIS_MESSAGE_CODE = "_Code";

    //Redis存储文章阅读数量
    public static final String ARTICLE_READ_TIME = "Article_Read_Time";

    //发送验证码状态
    public enum SendoCodeStatusEnum{

        SEND_SUCCESS(20000,"发送成功"),
        INIT_AC_FAIL(20001,"初始化ascClient失败"),
        ASSEMBLY_FAIL(20002,"组装请求对象失败"),
        SEND_BUZY(20003,"此号码频繁发送验证码，暂时不能获取!"),
        UNKONW_WRONG(20004,"未知错误");

        SendoCodeStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        //比较Code
        public static SendoCodeStatusEnum codeOf(int code){
            for(SendoCodeStatusEnum sendoCodeStatusEnum : values()){
                if(sendoCodeStatusEnum.getCode() == code){
                    return sendoCodeStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    //缓存时间
    public interface CacheTime{
        int MESSAGE_SEND = 60*10; //消息初始缓存时间
        int MESSAGE_RESEND = 60*9;//消息重发缓存时间标志
    }

    //请求状态
    public enum LoginStatusEnum{

        LOGIN_SUCCESS(10000,"登录成功"),
        NOT_FIND_USER(10001,"未找到用户"),
        WRONG_PASS(10002,"密码错误"),
        USER_LOCKED(10003,"用户被锁定，请联系管理员解锁"),
        WRONG_VERIFICATION_CODE(10004,"验证码错误");

        LoginStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;
        public String getValue() {
            return value;
        }
        public int getCode() {
            return code;
        }
        public static LoginStatusEnum codeOf(int code){
            for(LoginStatusEnum loginStatusEnum : values()){
                if(loginStatusEnum.getCode() == code){
                    return loginStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }


    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    public interface Cart{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }


    public enum OrderStatusEnum{
        CANCELED(0,"已取消"),
        NO_PAY(10,"未支付"),
        PAID(20,"已付款"),
        SHIPPED(40,"已发货"),
        ORDER_SUCCESS(50,"订单完成"),
        ORDER_CLOSE(60,"订单关闭");


        OrderStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }
    public interface  AlipayCallback{
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }



    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");

        PaymentTypeEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }
}
