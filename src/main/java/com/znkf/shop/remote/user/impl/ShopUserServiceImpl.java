package com.znkf.shop.remote.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.common.utils.DateUtils;
import com.znkf.shop.modules.user.dao.ShopUserDao;
import com.znkf.shop.modules.user.entity.ShopUser;
import com.znkf.shop.remote.user.IShopUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 武海升
 * @date 2018/7/4 18:45
 */
@Service(value = "apiShopUserService")
@Slf4j
public class ShopUserServiceImpl extends BaseService implements IShopUserService {

    @Autowired
    private ShopUserDao shopUserDao;

    @Override
    public String getUserInfo(Integer userId) {

        BaseVo baseVo = new BaseVo();
        try {
            if (userId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            ShopUser shopUser = shopUserDao.get(String.valueOf(userId));
            if(shopUser == null){
                baseVo.setResult(210000);
                return this.getJsonString(baseVo);
            }
            JSONObject userInfo = new JSONObject();
            userInfo.put("userId",shopUser.getId());
            userInfo.put("userName",shopUser.getUserName());
            userInfo.put("gender",shopUser.getGender());
            userInfo.put("birthday",DateUtils.formatDate(shopUser.getBirthday(),"yyyy-MM-dd"));
            userInfo.put("age",getAgeByBirth(shopUser.getBirthday()));
            userInfo.put("avatar",ConfigConstants.SHOP_PICTURE_BASE_PATH+shopUser.getAvatar());
            baseVo.setSuccessResult(210001,userInfo);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String updateUserInfo(String args) {
        BaseVo baseVo = new BaseVo();
        try {

            JSONObject userObj = JSONObject.parseObject(args);
            Integer userId = userObj.getInteger("userId");
            Integer gender = userObj.getInteger("gender");
            String userName = userObj.getString("userName");
            Date birthday = userObj.getDate("birthday");
            if (userId ==null || gender ==null || userName ==null || birthday ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }

            ShopUser shopUser = shopUserDao.get(String.valueOf(userId));
            if(shopUser == null){
                baseVo.setResult(210000);
                return this.getJsonString(baseVo);
            }
            shopUser.setGender(gender);
            shopUser.setUserName(userName);
            shopUser.setBirthday(birthday);
            shopUserDao.update(shopUser);
            baseVo.setSuccessResult(210002,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    private static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }
}
