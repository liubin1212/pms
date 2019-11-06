package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.bean.CustomerExample;
import com.ujiuye.cust.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CustomerServiceImp implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    public void addCustomer(Customer customer){
        customer.setAddtime(new Date());
        customerMapper.insert(customer);
    }
    @Override
    public List<Customer> findAllCustomer() {
        return customerMapper.selectByExample(new CustomerExample());
    }

    @Override
    public Customer findCustomerById(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    @Override
    public boolean updateCustomerById(Customer customer) {
        customer.setAddtime(new Date());
        int row = customerMapper.updateByPrimaryKey(customer);
        if (row > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomers(Integer[] ids) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        List<Integer> integers = Arrays.asList(ids);
        criteria.andIdIn(integers);
        int i = customerMapper.deleteByExample(example);
        if (i> 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        if (cid == 0){
            criteria.andComnameLike("%"+keyword+"%");
            CustomerExample.Criteria criteria1 = example.createCriteria();
            criteria1.andCompanypersonLike("%"+keyword+"%");
            example.or(criteria1);
        }else if (cid == 1){
            criteria.andComnameLike("%"+keyword+"%");
        }else{
            criteria.andCompanypersonLike("%"+keyword+"%");
        }

        if (orderby == 1){
            example.setOrderByClause("id desc");
        }

        List<Customer> customers = customerMapper.selectByExample(example);
        return customers;
    }


}
