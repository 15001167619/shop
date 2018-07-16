package com.znkf.shop.remote.commodity;

/**
 * @author 武海升
 * @date 2018/7/6 10:31
 */
public interface ICommodityService {

    /**
     * @author 武海升
     * @param pageNo 当前页码
     * @param pageSize 每页显示数
     * @param isHot 是否推荐  0 正常 1 热门推荐项目 默认全部
     * @desc 获取商品列表
     */
    String getCommodityList(String args);
    /**
     * @author 武海升
     * @param commodityId 商品Id
     * @desc 获取商品详情
     */
    String getCommodityDetails(Integer commodityId);

}
