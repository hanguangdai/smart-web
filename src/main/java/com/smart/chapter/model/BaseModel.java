package com.smart.chapter.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @description: 实体对象抽象类
 * @author: daihanguang
 * @create: 2019-05-06 16:10
 */
public abstract class BaseModel implements Serializable {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
