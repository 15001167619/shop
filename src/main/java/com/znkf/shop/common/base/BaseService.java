package com.znkf.shop.common.base;

import com.alibaba.fastjson.JSON;

/**
 * @author 武海升
 * @date 2018/7/3 15:09
 */
public class BaseService {

    /**
     * 获取JSON字符串
     * @param data
     * @return
     */
    public String getJsonString(Object data){
        return JSON.toJSONString(data);
    }

    public String stripHtml(String content) {
        if(content == null){
            return content;
        }
        content = content.replaceAll("<p .*?>", "\r\n");
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

}
