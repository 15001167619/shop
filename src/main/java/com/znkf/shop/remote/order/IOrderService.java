package com.znkf.shop.remote.order;

/**
 * @author 武海升
 * @date 2018/7/6 13:53
 */
public interface IOrderService {

    /**
     * @author 武海升
     * @desc 添加订单
     * @param userId 用户ID
     * @param mobile 手机号
     * @param commodityId 商品Id
     * @param consignee 收货人
     * @param address 收货地址
     * @param color 商品规格颜色
     * @param size 商品规格尺码
     * @param pic 商品规格图
     */
    String addOrder(String args);
    /**
     * @author 武海升
     * @desc 取消订单
     * @param userId 用户ID
     * @param orderId 订单Id
     */
    String delOrder(String args);
    /**
     * @author 武海升
     * @desc 获取我的订单列表
     * @param userId 用户ID
     * @param orderStatus 订单状态  不传参数（获取全部） 0待付款1 待发货2待收货3待评价
     * @param pageNo 当前页码
     * @param pageSize 每页显示数
     */
    String getOrderList(String args);

    /**
     * @author 武海升
     * @desc 修改我的订单 状态
     * @param userId 用户ID
     * @param orderStatus 订单状态
     * @param orderId 订单Id
     */
    String updateOrder(String args);
}
