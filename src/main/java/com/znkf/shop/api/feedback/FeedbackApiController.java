package com.znkf.shop.api.feedback;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.feedback.IFeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/10 13:58
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/feedback", produces = "application/json;charset=UTF-8")
public class FeedbackApiController extends ApiBaseController {

    @Resource(name = "apiFeedbackService")
    private IFeedbackService feedbackService;

    @ResponseBody
    @RequestMapping(value = "addFeedback", method = RequestMethod.POST)
    public String addFeedback(@RequestParam(value = "feedbackDesc",required = false) String feedbackDesc,
                              @RequestParam(value = "picUrl",required = false) String picUrl,
                              @RequestParam("userId") Integer userId) {
        if (userId == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("feedbackDesc", feedbackDesc);
        jsonObj.put("userId", userId);
        jsonObj.put("picUrl", picUrl);
        return feedbackService.addFeedback(jsonObj.toJSONString());
    }

}
