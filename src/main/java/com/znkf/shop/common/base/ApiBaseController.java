package com.znkf.shop.common.base;

import com.alibaba.fastjson.JSONObject;

public class ApiBaseController {

    public String getErrorObject() {
        return baseObject(false,10000,true,"传入参数值为空");
    }
    public String smsCodeExistObject() {
        return baseObject(false,202,true,"短信验证码已发送");
    }
    public String smsCodeErrorObject() {
        return baseObject(false,201,true,"短信验证码发送失败");
    }
    public String smsCodeSuccessObject() {
        return baseObject(true,200,true,"短信验证码成功");
    }

    public String baseObject(Object data,Integer statusCode,Boolean isSuccess,String desc ) {
        JSONObject shopErrorObj = new JSONObject();
        shopErrorObj.put("data",data);
        shopErrorObj.put("statusCode",statusCode);
        shopErrorObj.put("isSuccess",isSuccess);
        shopErrorObj.put("dataDesc",desc);
        return shopErrorObj.toJSONString();
    }




}
