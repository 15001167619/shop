package com.znkf.shop.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.znkf.shop.common.config.Const;

/**
 * @author YiTian
 * @version V1.0
 * @package com.zkhc.znkf.common.util
 * @description 短信发送工具
 * @email yitian@ai-kang.cn
 * Create Time 2017/9/4
 */
public class DysmsUtil {

    private final static String SEND_SUCCESS = "OK";
    private final static String BUSINESS_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL";

    public static int sendSMS(String phone, String code) {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        //短信API产品名称（短信产品名固定，无需修改）
        final String product = "Dysmsapi";
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIVchpyxzXHVU3";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "SunvwkNjZZy9OFu4vkzZJsirCVz7fX";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
            return Const.SendoCodeStatusEnum.SEND_SUCCESS.getCode();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("智能康复");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_92410010");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"number\":\"" + code + "\"}");
        //可选-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            return Const.SendoCodeStatusEnum.ASSEMBLY_FAIL.getCode();
        }
        if (sendSmsResponse.getCode() != null && SEND_SUCCESS.equals(sendSmsResponse.getCode())) {
            //请求成功
            return Const.SendoCodeStatusEnum.SEND_SUCCESS.getCode();
        } else if (BUSINESS_LIMIT_CONTROL.equals(sendSmsResponse.getCode())) {
            System.out.println("此号码频繁发送验证码，暂时不能获取！");
            return Const.SendoCodeStatusEnum.SEND_BUZY.getCode();
        }
        return Const.SendoCodeStatusEnum.UNKONW_WRONG.getCode();//未知错误
    }

    public static void main(String[] args) {
        int i = DysmsUtil.sendSMS("15001167619", "123456");
        System.out.println(i);
    }
}
