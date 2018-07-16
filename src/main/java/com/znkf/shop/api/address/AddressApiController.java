package com.znkf.shop.api.address;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.ApiBaseController;
import com.znkf.shop.remote.address.IAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/7/5 17:54
 */
@Controller
@WebAppConfiguration
@RequestMapping(value = "${apiPath}/address", produces = "application/json;charset=UTF-8")
public class AddressApiController extends ApiBaseController {

    @Resource(name = "apiAddressService")
    private IAddressService addressService;

    @ResponseBody
    @RequestMapping(value = "getAddressList", method = RequestMethod.GET)
    public String getAddressList(@RequestParam("userId") Integer userId,
                                 @RequestParam("pageNo") Integer pageNo,
                                 @RequestParam("pageSize") String pageSize
    ) {
        if (userId == null || pageNo == null || pageSize == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", userId);
        jsonObj.put("pageNo", pageNo);
        jsonObj.put("pageSize", pageSize);
        return addressService.getAddressList(jsonObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "getProvinceList", method = RequestMethod.GET)
    public String getProvinceList() {
        return addressService.getProvinceList();
    }

    @ResponseBody
    @RequestMapping(value = "getCityList", method = RequestMethod.GET)
    public String getCityList(@RequestParam("provinceId") Integer provinceId
    ) {
        if (provinceId == null) {
            return getErrorObject();
        }
        return addressService.getCityList(provinceId);
    }

    @ResponseBody
    @RequestMapping(value = "getAreaList", method = RequestMethod.GET)
    public String getAreaList(@RequestParam("cityId") Integer cityId
    ) {
        if (cityId == null) {
            return getErrorObject();
        }
        return addressService.getAreaList(cityId);
    }

    @ResponseBody
    @RequestMapping(value = "saveAddress", method = RequestMethod.POST)
    public String saveAddress(@RequestParam("name") String name,@RequestParam("mobile")String mobile,
                              @RequestParam(value = "address",required = false) String address,
                              @RequestParam("provinceId") Integer provinceId,
                              @RequestParam("cityId") Integer cityId,
                              @RequestParam("userId") Integer userId,
                              @RequestParam("isDefault") Integer isDefault,
                              @RequestParam("areaId") Integer areaId) {
        if (name == null || mobile == null || provinceId == null || cityId == null ||
                userId == null  || areaId == null || isDefault == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", name);
        jsonObj.put("mobile", mobile);
        jsonObj.put("address", address);
        jsonObj.put("provinceId", provinceId);
        jsonObj.put("cityId", cityId);
        jsonObj.put("userId", userId);
        jsonObj.put("areaId", areaId);
        jsonObj.put("isDefault", isDefault);
        return addressService.saveAddress(jsonObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "updateAddress", method = RequestMethod.POST)
    public String updateAddress( @RequestParam("name") String name,
                                 @RequestParam("mobile")String mobile,
                                 @RequestParam(value = "address",required = false) String address,
                                 @RequestParam("provinceId") Integer provinceId,
                                 @RequestParam("cityId") Integer cityId,
                                 @RequestParam("userId") Integer userId,
                                 @RequestParam("isDefault") Integer isDefault,
                                 @RequestParam("addressId") Integer addressId,
                                 @RequestParam("areaId") Integer areaId
    ) {
        if (name == null || mobile == null || provinceId == null || cityId == null ||
                userId == null || addressId == null || areaId == null || isDefault == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", name);
        jsonObj.put("mobile", mobile);
        jsonObj.put("address", address);
        jsonObj.put("provinceId", provinceId);
        jsonObj.put("cityId", cityId);
        jsonObj.put("userId", userId);
        jsonObj.put("areaId", areaId);
        jsonObj.put("isDefault", isDefault);
        jsonObj.put("addressId", addressId);
        return addressService.updateAddress(jsonObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "delAddress", method = RequestMethod.POST)
    public String delAddress(@RequestParam("addressId") Integer addressId,@RequestParam("userId") Integer userId
    ) {
        if (addressId == null || userId == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", userId);
        jsonObj.put("addressId", addressId);
        return addressService.delAddress(jsonObj.toJSONString());
    }

    @ResponseBody
    @RequestMapping(value = "getAddressDetails", method = RequestMethod.GET)
    public String getAddressDetails(@RequestParam("addressId") Integer addressId,@RequestParam("userId") Integer userId
    ) {
        if (addressId == null || userId == null) {
            return getErrorObject();
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", userId);
        jsonObj.put("addressId", addressId);
        return addressService.getAddressDetails(jsonObj.toJSONString());
    }



}
