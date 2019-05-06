package com.smart.chapter.service;

import com.smart.chapter.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * @description: 客户信息服务层
 * @author: daihanguang
 * @create: 2019-05-05 09:58
 */
public interface CustomerService {

    /**
     * 获取客户列表
     * @param keyword
     * @return
     */
    List<Customer> getCustomerList(String keyword);

    /**
     * 根据客户id获取对象
     * @param id
     * @return
     */
    Customer getCustomerById(Long id);

    /**
     * 创建客户
     * @param fieldMap
     * @return
     */
    boolean createCustomer(Map<String, Object> fieldMap);

    /**
     * 修改客户信息
     * @param id
     * @param fieldMap
     * @return
     */
    boolean updateCustomer(Long id, Map<String, Object> fieldMap);

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    boolean delateCustomer(Long id);

}
