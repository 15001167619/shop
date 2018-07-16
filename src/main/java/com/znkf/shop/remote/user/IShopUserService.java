package com.znkf.shop.remote.user;

/**
 * @author 武海升
 * @date 2018/7/4 18:42
 */
public interface IShopUserService {

    /**
     * @author 武海升
     * @desc  获取用户详情
     * @param userId 用户ID
     */
    String getUserInfo(Integer userId);

    /**
     * @author 武海升
     * @desc  更新用户
     * @param args 用户信息
     */
    String updateUserInfo(String args);


}
