package com.znkf.shop.remote.coupon;

/**
 * @author 武海升
 * @date 2018/7/10 14:21
 */
public interface ICouponService {

    /**
     * @author 武海升
     * @param pageNo 当前页码
     * @param pageSize 每页显示数
     * @param userId 用户ID
     * @desc 获取优惠券列表
     */
    String getCouponList(String args);

}
