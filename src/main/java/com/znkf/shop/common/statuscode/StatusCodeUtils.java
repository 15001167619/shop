package com.znkf.shop.common.statuscode;

/**
 * @author 武海升
 * @date 2018/7/3 14:48
 */
public class StatusCodeUtils {

    /**
     * 方法名: getStatusDesc
     * 方法描述: 通过状态码获取描述
     * 参数 @param statusCode 状态码
     * 参数 @return 参数说明   状态描述
     * 返回类型 String 返回类型
     * @throws
     */
    public static String getStatusDesc(Integer statusCode) {

        return StatusCodeReloadableConfig.getProperty(String.valueOf(statusCode), "100000");
    }

}
