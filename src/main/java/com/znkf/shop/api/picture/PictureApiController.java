package com.znkf.shop.api.picture;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.config.ConfigConstants;
import com.znkf.shop.common.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 武海升
 * @date 2018/7/10 13:25
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/picture", produces = "application/json;charset=UTF-8")
public class PictureApiController {


    @RequestMapping(value = "feedbackAlbumUpload")
    @ResponseBody
    public JSONObject feedbackAlbumUpload(MultipartFile file) {
        JSONObject jsonObject = FileUtils.setFilenamePath(file, ConfigConstants.FEEDBACK_ALBUM);
        //上传图片
        FileUtils.uploadFile(file,jsonObject.getString("filenamePath"));
        JSONObject object = new JSONObject();
        object.put("basePath",ConfigConstants.SHOP_PICTURE_BASE_PATH);
        object.put("picPath",jsonObject.getString("returnFilenamePath"));
        return object;
    }

}
