package com.znkf.shop.remote.help;

/**
 * @author 武海升
 * @date 2018/7/3 18:17
 */
public interface IHelpService {

    /**
     * @author 武海升
     * @param pageNo 当前页码
     * @param pageSize 每页显示数
     * @desc 获取帮助中心列表
     */
    String getHelpList(String args);

    /**
     * @author 武海升
     * @desc 获取帮助中心详情
     * @param helpId 帮助中心Id
     */
    String getHelpDetails(Integer helpId);
}
