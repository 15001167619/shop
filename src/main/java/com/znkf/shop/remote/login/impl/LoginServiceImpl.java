package com.znkf.shop.remote.login.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.modules.user.dao.ShopUserDao;
import com.znkf.shop.modules.user.entity.ShopUser;
import com.znkf.shop.remote.login.ILoginService;
import com.znkf.shop.remote.redis.ICoreRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 武海升
 * @date 2018/7/11 10:49
 */
@Service(value = "apiLoginService")
@Slf4j
public class LoginServiceImpl extends BaseService implements ILoginService {

    @Autowired
    private ICoreRedisService redisService;

    @Autowired
    private ShopUserDao shopUserDao;

    @Override
    public String signInOrRegister(String args) {
        BaseVo baseVo = new BaseVo();
        try {

            JSONObject userObj = JSONObject.parseObject(args);
            String code = userObj.getString("code");
            String mobile = userObj.getString("mobile");
            String openId = userObj.getString("openId");
            if (mobile ==null || code ==null || openId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            String redisValueByKey = redisService.getRedisValueByKey(mobile + "_sms_code");
            ShopUser userInfo = shopUserDao.getUserInfoByMobile(mobile);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("openId",openId);

            if(userInfo == null){//注册
                if(redisValueByKey==null){//验证码已经失效
                    baseVo.setSuccessResult(210004,false);
                    return this.getJsonString(baseVo);
                }else if(!code.equals(redisValueByKey)){//验证码错误
                    baseVo.setSuccessResult(210003,false);
                    return this.getJsonString(baseVo);
                }else {//校验成功
                    ShopUser shopUser = new ShopUser(mobile, openId);
                    shopUserDao.insert(shopUser);
                    jsonObject.put("userId",shopUser.getId());
                    baseVo.setSuccessResult(210005,jsonObject);
                    return this.getJsonString(baseVo);
                }
            }else{//登录
                if(redisValueByKey==null){//验证码已经失效
                    baseVo.setSuccessResult(210004,false);
                    return this.getJsonString(baseVo);
                }else if(!code.equals(redisValueByKey)){//验证码错误
                    baseVo.setSuccessResult(210003,false);
                    return this.getJsonString(baseVo);
                }else {//校验成功
                    jsonObject.put("userId",userInfo.getId());
                    baseVo.setSuccessResult(210006,jsonObject);
                    return this.getJsonString(baseVo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }
}
