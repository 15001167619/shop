package com.znkf.shop.api.sms;

import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.common.utils.DysmsUtil;
import com.znkf.shop.common.utils.IdGen;
import com.znkf.shop.remote.redis.ICoreRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/11 9:23
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/sms", produces = "application/json;charset=UTF-8")
@Slf4j
public class SmsApiController extends ApiBaseController {

    @Resource(name = "redisService")
    private ICoreRedisService coreRedisService;

    @ResponseBody
    @RequestMapping(value = "sendCode", method = RequestMethod.POST)
    public String sendCode(@RequestParam("phone") String phone){
        if (phone == null) {
            return getErrorObject();
        }
        String serverCodeKey = phone+"_sms_code";
        String redisValueByKey = coreRedisService.getRedisValueByKey(serverCodeKey);
        if(redisValueByKey==null){
            //生成随机6位数
            String messageCode = IdGen.createMessageCode();
            log.info("messageCode=====>"+messageCode);
            int i = DysmsUtil.sendSMS(phone, messageCode);
            if(i==20000){
                coreRedisService.addToRedis(serverCodeKey,messageCode,60*10);
                return smsCodeSuccessObject();
            }else{
                return smsCodeErrorObject();
            }
        }else{
            return smsCodeExistObject();
        }
    }
}
