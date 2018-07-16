package com.znkf.shop.remote.feedback;

/**
 * @author 武海升
 * @date 2018/7/10 13:48
 */
public interface IFeedbackService {

    /**
     * @author 武海升
     * @param userId 用户ID
     * @param feedbackDesc 意见描述
     * @param picUrl 意见描述图片
     * @desc 添加意见反馈
     */
    String addFeedback(String args);

}
