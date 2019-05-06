package com.smart.chapter.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @description: 客户信息实体对象
 * @author: daihanguang
 * @create: 2019-04-29 11:47
 */
public class Customer extends BaseModel{

    /** 标记id*/
    private long id;
    /** 客户名称*/
    private String name;
    /** 联系人*/
    private String contacts;
    /** 联系电话*/
    private String telphone;
    /** 联系邮件*/
    private String email;
    /** 备注*/
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
