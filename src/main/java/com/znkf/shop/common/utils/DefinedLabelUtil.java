package com.znkf.shop.common.utils;

public class DefinedLabelUtil {

    public static String getTypeName(Integer type,String typeName){
        if(type==null)return null;
        switch(type){
            case 1:
                typeName = "省";
                return typeName;
            case 2:
                typeName = "市";
                return typeName;
            case 3:
                typeName = "区";
                return typeName;
            case 4:
                typeName = "街道";
                return typeName;
            default:
                return typeName;
        }
    }

}
