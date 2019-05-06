package com.smart.chapter.service.impl;

import com.smart.chapter.helper.DatabaseHelper;
import com.smart.chapter.model.Customer;
import com.smart.chapter.service.CustomerService;
import com.smart.chapter.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 客户信息实现类
 * @author: daihanguang
 * @create: 2019-05-05 09:59
 */
public final class CustomerServiceImpl implements CustomerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    /**
     * 获取客户列表
     *
     * @param keyword
     * @return
     */
    @Deprecated
    public List<Customer> getCustomerListDeprecated(String keyword) {
        Connection conn = null;
        List<Customer> customers = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from customers where 1 = 1 ");
        try {
            conn = DatabaseHelper.getConnection();
            PreparedStatement preparedStatement = null;
            if(StringUtils.isNotBlank(keyword)){
                sql.append(" and name like ? ");
                preparedStatement = conn.prepareStatement(sql.toString());
                preparedStatement.setString(1, keyword + "%");
            }else{
                preparedStatement = conn.prepareStatement(sql.toString());
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setContacts(rs.getString("contacts"));
                customer.setEmail(rs.getString("email"));
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setRemark(rs.getString("remark"));
                customer.setTelphone(rs.getString("telphone"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.info("excute sql fail", e);
        } finally {
            DatabaseHelper.closeCollection();
        }
        return customers;
    }

    /**
     * 获取客户列表
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Customer> getCustomerList(String keyword) {
        List<Customer> customers = null;
        Connection conn = DatabaseHelper.getConnection();
        StringBuilder sql = new StringBuilder("select * from customers where 1 = 1 ");
        try {
            List<Object> conditioValues = new ArrayList<>();
            if(StringUtil.isNotEmpty(keyword)){
                sql.append(" and name like ? ");
                conditioValues.add(keyword + "%");
            }
            customers = DatabaseHelper.queryEntityList(conn, Customer.class, sql.toString(), conditioValues.toArray());
        }finally {
            DatabaseHelper.closeCollection();
        }
        return customers;
    }

    /**
     * 根据客户id获取对象
     *
     * @param id
     * @return
     */
    @Override
    public Customer getCustomerById(Long id) {
        return null;
    }

    /**
     * 创建客户
     *
     * @param fieldMap
     * @return
     */
    @Override
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return false;
    }

    /**
     * 修改客户信息
     *
     * @param id
     * @param fieldMap
     * @return
     */
    @Override
    public boolean updateCustomer(Long id, Map<String, Object> fieldMap) {
        return false;
    }

    /**
     * 删除客户信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean delateCustomer(Long id) {
        return false;
    }
}
