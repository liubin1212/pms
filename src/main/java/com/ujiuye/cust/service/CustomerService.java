package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;

import java.util.List;

public interface CustomerService {

   public void addCustomer(Customer customer);

   List<Customer> findAllCustomer();

   Customer findCustomerById(Integer id);

   boolean updateCustomerById(Customer customer);

   boolean deleteCustomers(Integer[] ids);

   List<Customer> search(Integer cid, String keyword, Integer orderby);
}
