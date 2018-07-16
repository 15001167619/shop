package com.znkf.shop.remote.feedback.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.modules.feedback.dao.ShopFeedbackDao;
import com.znkf.shop.modules.feedback.entity.ShopFeedback;
import com.znkf.shop.remote.feedback.IFeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 武海升
 * @date 2018/7/10 13:51
 */
@Service(value = "apiFeedbackService")
@Slf4j
public class FeedbackServiceImpl extends BaseService implements IFeedbackService {

    @Autowired
    private ShopFeedbackDao feedbackDao;

    @Override
    public String addFeedback(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject addressObj = JSONObject.parseObject(args);
            String feedbackDesc = addressObj.getString("feedbackDesc");
            String picUrl = addressObj.getString("picUrl");
            Integer userId = addressObj.getInteger("userId");
            if (userId ==null ){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            feedbackDao.insert(new ShopFeedback(userId,feedbackDesc,picUrl));
            baseVo.setSuccessResult(220026,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

}
