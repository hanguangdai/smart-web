package com.smart.chapter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description: 配置文件读取工具
 * @author: daihanguang
 * @create: 2019-05-05 11:24
 */
public final class PropsUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName){
        Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(inputStream == null){
                throw new FileNotFoundException(fileName + " file is not found");
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.info("load properties file failure", e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.info("close input stream failure", e);
                }
            }
        }
        return properties;
    }

    /**
     * 获取字符型属性值（默认值为空字符串）
     * @param prop
     * @param key
     * @return
     */
    public static String getString(Properties prop, String key){
        return getString(prop, key, "");
    }

    /**
     * 获取字符型属性值（可指定默认值）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties props, String key, String defaultValue){
        String value = defaultValue;
        if(props.containsKey(key)){
            value = props.getProperty(key);
        }
        return value;
    }


    /**
     * 获取数字属性值
     * @param props
     * @param key
     * @return
     */
    public  static int getInt(Properties props, String key){
        return getInt(props, key, 0);
    }

    /**
     * 获取数字属性值（可指定默认值）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Properties props, String key, int defaultValue){
        int value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取数字属性值
     * @param props
     * @param key
     * @return
     */
    public static double getDouble(Properties props, String key){
        return getDouble(props, key, 0D);
    }

    /**
     * 获取数字属性值（可指定默认值）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static double getDouble(Properties props, String key, double defaultValue){
        double value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castDouble(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔属性值
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties props, String key){
        return getBoolean(props, key, false);
    }

    /**
     * 获取布尔属性值（可指定默认值）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Properties props, String key, boolean defaultValue){
        boolean value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }


}
