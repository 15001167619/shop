package com.znkf.shop.common.exception;

import com.znkf.shop.common.enums.ResultEnum;

/**
 * @author 武海升
 * @date 2018/7/6 17:57
 */
public class SellException extends RuntimeException  {

    private Integer code;

    public SellException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

}
