package com.znkf.shop.common.config;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReloadableConfig {

	private ReloadableConfig(){}
	private static Properties prop;
	private static InputStream in;

	static {
		try {
			prop = new Properties();
			in = ReloadableConfig.class.getResourceAsStream("/config.properties");
			prop.load(in);
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
