package com.smart.chapter.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @description: 字符串工具类
 * @author: daihanguang
 * @create: 2019-05-06 15:28
 */
public final class StringUtil {

    /**
     * 判读字符是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str != null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判读字符串不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }


}
