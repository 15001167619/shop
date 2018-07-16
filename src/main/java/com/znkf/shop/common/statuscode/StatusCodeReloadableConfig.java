package com.znkf.shop.common.statuscode;

import com.znkf.shop.common.utils.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author 武海升
 * @date 2018/7/3 14:49
 */
public class StatusCodeReloadableConfig {

    private StatusCodeReloadableConfig(){}
    private static Properties prop;
    private static InputStream in;

    static {
        try {
            prop = new Properties();
            in = StatusCodeReloadableConfig.class.getResourceAsStream("/config/status_des.properties");
            prop.load(new InputStreamReader(in, "UTF-8"));
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("reloadableConfig  properties file not find ");
        } catch (IOException e) {
            System.out.println("reloadableConfig initial faile");
        } catch (Exception e) {
            System.out.println("property file not exits");
        }

    }




    public static String getProperty(String key,String defaultValue)
    {
        if (prop == null) {
            return null;
        }
        String value = prop.getProperty(key);
        if(value == null) return defaultValue;
        return value;
    }

    public static Integer getInt(String key,int defaultVal)
    {
        if (prop == null) {
            return null;
        }
        String value = prop.getProperty(key);
        if(StringUtils.isNumeric(value)){
            return Integer.parseInt(value);
        }
        return defaultVal;
    }

}
