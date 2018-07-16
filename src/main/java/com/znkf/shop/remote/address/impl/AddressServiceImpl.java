package com.znkf.shop.remote.address.impl;

import com.alibaba.fastjson.JSONObject;
import com.znkf.shop.common.base.BaseService;
import com.znkf.shop.common.base.BaseVo;
import com.znkf.shop.common.persistence.Page;
import com.znkf.shop.modules.address.dao.ShopAddressDao;
import com.znkf.shop.modules.address.entity.ShopAddress;
import com.znkf.shop.modules.address.service.ShopAddressService;
import com.znkf.shop.modules.region.dao.ShopRegionDao;
import com.znkf.shop.modules.region.entity.ShopRegion;
import com.znkf.shop.remote.address.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/7/5 16:44
 */
@Service(value = "apiAddressService")
@Slf4j
public class AddressServiceImpl extends BaseService implements IAddressService {

    @Autowired
    private ShopAddressDao shopAddressDao;

    @Autowired
    private ShopRegionDao shopRegionDao;

    @Autowired
    private ShopAddressService shopAddressService;

    @Override
    public String getProvinceList() {
        BaseVo baseVo = new BaseVo();
        try {
            List<Map<String,Object>> provinceList = new ArrayList<>();
            for (ShopRegion shopRegion :shopRegionDao.findList(new ShopRegion(1))
                    ) {
                Map<String,Object> province = new HashMap<>();
                province.put("provinceId",shopRegion.getId());
                province.put("provinceName",shopRegion.getName());
                provinceList.add(province);
            }
            baseVo.setSuccessResult(220008,provinceList);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String getCityList(Integer provinceId) {
        BaseVo baseVo = new BaseVo();
        try {
            List<Map<String,Object>> cityList = new ArrayList<>();
            for (ShopRegion shopRegion :shopRegionDao.findList(new ShopRegion(2,provinceId))
                    ) {
                Map<String,Object> city = new HashMap<>();
                city.put("cityId",shopRegion.getId());
                city.put("cityName",shopRegion.getName());
                cityList.add(city);
            }
            baseVo.setSuccessResult(220009,cityList);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String getAreaList(Integer cityId) {
        BaseVo baseVo = new BaseVo();
        try {
            List<Map<String,Object>> areaList = new ArrayList<>();
            for (ShopRegion shopRegion :shopRegionDao.findList(new ShopRegion(3,cityId))
                    ) {
                Map<String,Object> area = new HashMap<>();
                area.put("areaId",shopRegion.getId());
                area.put("areaName",shopRegion.getName());
                areaList.add(area);
            }
            baseVo.setSuccessResult(220010,areaList);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String getAddressList(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject addressObj = JSONObject.parseObject(args);
            Integer pageNo = addressObj.getInteger("pageNo");
            Integer pageSize = addressObj.getInteger("pageSize");
            Integer userId = addressObj.getInteger("userId");
            if (pageNo ==null || pageSize ==null ||userId == null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            Page<ShopAddress> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Page<ShopAddress> listPage = shopAddressService.findAddressPage(page,new ShopAddress(userId));
            List<Map<String,Object>> addressList = new ArrayList<>();
            for (ShopAddress address: listPage.getList()) {
                Map<String,Object> map = new HashMap<>();
                map.put("addressId",address.getId());
                map.put("name",address.getName());
                map.put("mobile",address.getMobile());
                map.put("isDefault",address.getIsDefault());
                map.put("address",address.getAddress());
                map.put("provinceId",address.getProvinceId());
                map.put("provinceName",address.getProvinceName());
                map.put("cityId",address.getCityId());
                map.put("cityName",address.getCityName());
                map.put("areaId",address.getAreaId());
                map.put("areaName",address.getAreaName());
                addressList.add(map);
            }
            JSONObject json = new JSONObject();
            json.put("addressList", addressList);
            json.put("sumCount", listPage.getCount());
            json.put("pages", (null == listPage || listPage.getList().size()==0) == true ? 0 : listPage.getTotalPage());
            baseVo.setSuccessResult(220011,json);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String saveAddress(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject addressObj = JSONObject.parseObject(args);
            String name = addressObj.getString("name");
            String mobile = addressObj.getString("mobile");
            String address = addressObj.getString("address");
            Integer provinceId = addressObj.getInteger("provinceId");
            Integer cityId = addressObj.getInteger("cityId");
            Integer userId = addressObj.getInteger("userId");
            Integer areaId = addressObj.getInteger("areaId");
            Integer isDefault = addressObj.getInteger("isDefault");
            if (name ==null || provinceId ==null ||userId == null || mobile ==null ||cityId == null ||areaId ==null || isDefault ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            //默认地址修改
            if(isDefault==1){
                //设置默认地址为普通地址
                shopAddressDao.setDefaultValueZero(userId);
            }
            shopAddressDao.insert(new ShopAddress(name,userId,provinceId,cityId,areaId,address,mobile,isDefault));
            baseVo.setSuccessResult(220012,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String updateAddress(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject addressObj = JSONObject.parseObject(args);
            String addressId = addressObj.getString("addressId");
            String name = addressObj.getString("name");
            String mobile = addressObj.getString("mobile");
            String address = addressObj.getString("address");
            Integer provinceId = addressObj.getInteger("provinceId");
            Integer cityId = addressObj.getInteger("cityId");
            Integer userId = addressObj.getInteger("userId");
            Integer areaId = addressObj.getInteger("areaId");
            Integer isDefault = addressObj.getInteger("isDefault");
            if (name ==null || provinceId ==null ||userId == null || mobile ==null ||cityId == null ||areaId ==null || isDefault ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            ShopAddress shopAddress = shopAddressDao.get(addressId);
            if(shopAddress == null){
                baseVo.setResult(220015);
                return this.getJsonString(baseVo);
            }else{
                //默认地址修改
                if(isDefault==1 && shopAddress.getIsDefault() != isDefault){
                    //设置默认地址为普通地址
                    shopAddressDao.setDefaultValueZero(userId);
                }
                shopAddressDao.update(shopAddress.upDate(shopAddress,name,userId,provinceId,cityId,areaId,address,mobile,isDefault));
            }
            baseVo.setSuccessResult(220013,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String delAddress(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject addressObj = JSONObject.parseObject(args);
            Integer addressId = addressObj.getInteger("addressId");
            Integer userId = addressObj.getInteger("userId");
            if (addressId ==null || userId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            shopAddressDao.delAddress(addressId,userId);
            baseVo.setSuccessResult(220014,true);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }

    @Override
    public String getAddressDetails(String args) {
        BaseVo baseVo = new BaseVo();
        try {
            JSONObject addressObj = JSONObject.parseObject(args);
            Integer addressId = addressObj.getInteger("addressId");
            Integer userId = addressObj.getInteger("userId");
            if (addressId ==null || userId ==null){
                baseVo.setResult(100001);
                return this.getJsonString(baseVo);
            }
            ShopAddress addressInfo = shopAddressDao.getAddressDetails(addressId, userId);
            if(addressInfo==null){
                baseVo.setResult(220025);
                return this.getJsonString(baseVo);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("addressId",addressInfo.getId());
            jsonObject.put("name",addressInfo.getName());
            jsonObject.put("userId",addressInfo.getUserId());
            jsonObject.put("provinceId",addressInfo.getProvinceId());
            jsonObject.put("cityId",addressInfo.getCityId());
            jsonObject.put("address",addressInfo.getAddress());
            jsonObject.put("mobile",addressInfo.getMobile());
            jsonObject.put("areaId",addressInfo.getAreaId());
            jsonObject.put("isDefault",addressInfo.getIsDefault());
            baseVo.setSuccessResult(220024,jsonObject);
            return this.getJsonString(baseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getJsonString(baseVo);
        }
    }
}
