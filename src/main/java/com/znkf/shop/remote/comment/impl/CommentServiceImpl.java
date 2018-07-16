package com.znkf.shop.remote.comment.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.common.utils.DateUtils;
import com.znkf.shop.modules.comment.dao.ShopCommentDao;
import com.znkf.shop.modules.comment.entity.ShopComment;
import com.znkf.shop.modules.comment.service.ShopCommentService;
import com.znkf.shop.remote.comment.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/7/6 11:49
 */
@Service(value = "apiCommentService")
@Slf4j
public class CommentServiceImpl extends BaseService implements ICommentService {

    @Autowired
    private ShopCommentService shopCommentService;

    @Autowired
    private ShopCommentDao shopCommentDao;

    @Override
    public String getEvaluates(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject commodityObj = JSONObject.parseObject(args);
            Integer pageNo = commodityObj.getInteger("pageNo");
            Integer pageSize = commodityObj.getInteger("pageSize");
            Integer commodityId = commodityObj.getInteger("commodityId");
            Integer evaluateType = commodityObj.getInteger("evaluateType");
            if (pageNo ==null || pageSize ==null || commodityId ==null || evaluateType == null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            Page<ShopComment> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Page<ShopComment> listPage = shopCommentService.findShopCommentPage(page,new ShopComment(evaluateType,commodityId,0));
            List<Map<String,Object>> listComment = new ArrayList<>();
            for (ShopComment shopComment: listPage.getList()) {
                Map<String,Object> comment = new HashMap<>();
                comment.put("commodityId",shopComment.getId());
                comment.put("content",shopComment.getContent());
                comment.put("userId",shopComment.getUserId());
                comment.put("userName",shopComment.getUserName());
                comment.put("time",DateUtils.formatDate(shopComment.getAddTime(),"yyyy-MM-dd"));
                listComment.add(comment);
            }
            JSONObject json = new JSONObject();
            json.put("listComment", listComment);
            json.put("sumCount", listPage.getCount());
            json.put("pages", (null == listPage || listPage.getList().size()==0) == true ? 0 : listPage.getTotalPage());
            baseVo.setSuccessResult(220019,json);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String addEvaluates(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject commodityObj = JSONObject.parseObject(args);
            Integer userId = commodityObj.getInteger("userId");
            Integer businessId = commodityObj.getInteger("businessId");
            Integer businessType = commodityObj.getInteger("businessType");
            String content = commodityObj.getString("content");
            if (userId ==null || businessId ==null || businessType ==null || content == null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            shopCommentDao.insert(new ShopComment(businessType,businessId,content));
            baseVo.setSuccessResult(220020,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }
}
