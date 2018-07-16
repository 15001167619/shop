package com.znkf.shop.common.base;

import com.znkf.shop.common.statuscode.StatusCodeUtils;
import lombok.Data;

/**
 * @author 武海升
 * @date 2018/7/3 14:43
 */
@Data
public class BaseVo {

    private Object data = new Object();
    private Integer statusCode = 100000;
    private Boolean isSuccess = false;
    private String codeDesc = "服务器异常";


    public BaseVo(){

    }

    public BaseVo(Integer statusCode,String codeDesc){
        this.statusCode = statusCode;
        this.codeDesc = codeDesc;
    }

    public void setResult(Integer statusCode){
        this.statusCode = statusCode;
        this.codeDesc = StatusCodeUtils.getStatusDesc(statusCode);
    }

    public void setSuccessResult(Integer statusCode,Object data){
        this.data = data;
        this.isSuccess = true;
        this.statusCode = statusCode;
        this.codeDesc = StatusCodeUtils.getStatusDesc(statusCode);
    }
}
