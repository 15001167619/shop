package com.znkf.shop.remote.comment;

/**
 * @author 武海升
 * @date 2018/7/6 11:47
 */
public interface ICommentService {

    /**
     * @author 武海升
     * @param pageNo 当前页码
     * @param pageSize 每页显示数
     * @param commodityId 商品Id
     * @param evaluateType 评价类型 0 商品评价
     * @desc 获取评价列表
     */
    String getEvaluates(String args);

    /**
     * @author 武海升
     * @param commodityId 商品Id
     * @param evaluateType 评价类型 0 商品评价
     * @desc 添加评价
     */
    String addEvaluates(String args);

}
