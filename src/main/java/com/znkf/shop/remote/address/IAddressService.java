package com.znkf.shop.remote.address;

/**
 * @author 武海升
 * @date 2018/7/5 16:36
 */
public interface IAddressService {

    /**
     * @author 武海升
     * @desc 获取省
     */
    String getProvinceList();
    /**
     * @author 武海升
     * @desc 获取市
     */
    String getCityList(Integer provinceId);
    /**
     * @author 武海升
     * @desc 获取区/县
     */
    String getAreaList(Integer cityId);

    /**
     * @author 武海升
     * @desc 获取全部收货地址
     */
    String getAddressList(String args);

    /**
     * @author 武海升
     * @desc 新增收货地址
     */
    String saveAddress(String args);

    /**
     * @author 武海升
     * @desc 更新收货地址
     */
    String updateAddress(String args);

    /**
     * @author 武海升
     * @desc 删除收货地址
     */
    String delAddress(String args);

    /**
     * @author 武海升
     * @desc 获取收货地址
     */
    String getAddressDetails(String args);
}
