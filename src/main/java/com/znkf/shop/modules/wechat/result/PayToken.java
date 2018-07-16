package com.znkf.shop.modules.wechat.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 武海升
 * @date 2018/7/12 8:48
 */
@Data
public class PayToken implements Serializable {

    private static final long serialVersionUID = 457756492445408584L;
    private String accessToken;
    private long tokenCreateTime;
    private int expiresIn;


}
