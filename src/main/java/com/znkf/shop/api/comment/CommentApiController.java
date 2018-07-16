package com.znkf.shop.api.comment;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.comment.ICommentService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/6 11:45
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/comment", produces = "application/json;charset=UTF-8")
public class CommentApiController extends ApiBaseController {

    @Resource(name = "apiCommentService")
    private ICommentService commentService;

    @ResponseBody
    @RequestMapping(value = "getEvaluates", method = RequestMethod.GET)
    public String getEvaluates(@RequestParam("commodityId") Integer commodityId,
                               @RequestParam("evaluateType") Integer evaluateType,
                               @RequestParam("pageNo") Integer pageNo,
                               @RequestParam("pageSize") Integer pageSize){
        if (pageNo == null || pageSize ==null|| commodityId == null || evaluateType ==null) {
            return getErrorObject();
        }
        JSONObject evaluateObj = new JSONObject();
        evaluateObj.put("pageNo", pageNo);
        evaluateObj.put("pageSize", pageSize);
        evaluateObj.put("commodityId", commodityId);
        evaluateObj.put("evaluateType", evaluateType);
        return commentService.getEvaluates(evaluateObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "addEvaluates", method = RequestMethod.POST)
    public String addEvaluates(@RequestParam("userId") Integer userId,
                               @RequestParam("businessId") Integer businessId,
                               @RequestParam("businessType") Integer businessType,
                               @RequestParam("content") String content){
        if (userId == null || businessId ==null|| businessType == null || content ==null) {
            return getErrorObject();
        }
        JSONObject evaluateObj = new JSONObject();
        evaluateObj.put("userId", userId);
        evaluateObj.put("businessId", businessId);
        evaluateObj.put("businessType", businessType);
        evaluateObj.put("content", content);
        return commentService.addEvaluates(evaluateObj.toJSONString());
    }

}
