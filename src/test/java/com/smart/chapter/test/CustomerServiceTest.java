package com.smart.chapter.test;

import com.smart.chapter.model.Customer;
import com.smart.chapter.service.CustomerService;
import com.smart.chapter.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @description: 客户服务单元测试
 * @author: daihanguang
 * @create: 2019-05-05 10:27
 */
public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest(){
        this.customerService = new CustomerServiceImpl();
    }

    @Before
    public void init(){

    }

    @Test
    public void getCustomerListTest() throws Exception{
        List<Customer> customers = this.customerService.getCustomerList("测");
        System.out.println(customers);
    }

    @Test
    public void getCustomerById() {
    }

    @Test
    public void createCustomer() {
    }

    @Test
    public void updateCustomer() {
    }

    @Test
    public void delateCustomer() {
    }
}
