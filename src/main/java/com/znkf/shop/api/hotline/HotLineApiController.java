package com.znkf.shop.api.hotline;

import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.hotline.IHotLineService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/5 14:37
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/hotline", produces = "application/json;charset=UTF-8")
public class HotLineApiController extends ApiBaseController {

    @Resource(name = "apiHotLineService")
    private IHotLineService hotLineService;

    @ResponseBody
    @RequestMapping(value = "getHotLine", method = RequestMethod.GET)
    public String getHotLine() {
        return hotLineService.getHotLine();
    }
}
