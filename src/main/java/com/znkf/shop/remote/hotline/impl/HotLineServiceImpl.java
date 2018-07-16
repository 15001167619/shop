package com.znkf.shop.remote.hotline.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.utils.DateUtils;
import com.znkf.shop.modules.hotline.dao.ShopHotlineDao;
import com.znkf.shop.modules.hotline.entity.ShopHotline;
import com.znkf.shop.remote.hotline.IHotLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 武海升
 * @date 2018/7/4 18:39
 */
@Service(value = "apiHotLineService")
@Slf4j
public class HotLineServiceImpl extends BaseService implements IHotLineService {

    @Autowired
    private ShopHotlineDao shopHotlineDao;

    @Override
    public String getHotLine() {
        BaseVo baseVo = new BaseVo();
        try {
            List<ShopHotline> list = shopHotlineDao.findList(new ShopHotline(0));
            if(list!=null && list.size()>0){
                ShopHotline shopHotline = list.get(0);
                JSONObject hotLineObj = new JSONObject();
                hotLineObj.put("telephone",shopHotline.getTelephone());
                hotLineObj.put("beginTime",DateUtils.formatDate(shopHotline.getBeginTime(),"HH:mm"));
                hotLineObj.put("endTime",DateUtils.formatDate(shopHotline.getEndTime(),"HH:mm"));
                baseVo.setSuccessResult(220001,hotLineObj);
            }else{
                baseVo.setResult(220000);
                return this.getJsonString(baseVo);
            }
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }
}
