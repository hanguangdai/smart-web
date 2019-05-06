package com.smart.chapter.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * @description: 类型转换工具
 * @author: daihanguang
 * @create: 2019-05-05 14:07
 */
public class CastUtil {

    /**
     * 转换为字符类型
     * @param obj
     * @return
     */
    public static String castString(Object obj){
        return castString(obj, "");
    }
    /**
     * 转换为字符类型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue){
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转换为boolean类型
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj){
        return castBoolean(obj, Boolean.FALSE);
    }

    /**
     * 转换为boolean类型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj, boolean defaultValue){
        boolean value = defaultValue;
        if(obj != null){
            value = Boolean.valueOf(value);
        }
        return value;
    }

    /**
     * 转换为整数类型
     * @param obj
     * @return
     */
    public static int castInt(Object obj){
        return castInt(obj, 0);
    }

    /**
     * 转换为整数类型（需要提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj, int defaultValue){
        return castObj(obj, defaultValue, Integer::parseInt);
    }

    /**
     * 转换为Long类型
     * @param obj
     * @return
     */
    public static long castLong(Object obj){
        return castLong(obj, 0L);
    }

    /**
     * 转换为Long类型（需要默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj, long defaultValue){
        return castObj(obj, defaultValue, Long::parseLong);
    }

    /**
     * 转换类型为double类型
     * @param obj
     * @return
     */
    public static double castDouble(Object obj){
        return castDouble(obj, 0D);
    }

    /**
     * 转换类型为double类型（需传入默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj, double defaultValue){
        return castObj(obj, defaultValue, Double::parseDouble);
    }

    /**
     * 转换类型为BigDecimal
     * @param obj
     * @return
     */
    public static BigDecimal castBigDecimal(Object obj){
        return castBigDecimal(obj, BigDecimal.ZERO);
    }

    /**
     * 转换类型为BigDecimal(需传入默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static BigDecimal castBigDecimal(Object obj, BigDecimal defaultValue){
        return castObj(obj, defaultValue,  BigDecimal::new);
    }

    /**
     * 通用转换类型方法
     * @param obj
     * @param defaultValue
     * @param function
     * @param <T>
     * @return
     */
    public static <T> T castObj(Object obj, T defaultValue, Function<String, T> function){
        T value = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtils.isNotBlank(strValue)){
                try {
                    if(function != null){
                        value = function.apply(strValue);
                    }
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }
        return value;
    }

}
