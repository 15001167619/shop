package com.znkf.shop.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 武海升
 * @date 2018/7/6 17:56
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {

    WECHAT_MP_ERROR(20, "微信公众账号出现异常"),
    ;

    private Integer code;
    private String msg;

}
