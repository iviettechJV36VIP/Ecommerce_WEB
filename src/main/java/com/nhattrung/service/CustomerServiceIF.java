/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.Customer;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CustomerServiceIF {
    Customer getCustomerByUsername(String username);
    List<Customer> findAllCustomer();
    
    Customer getCustomerDetails();
    
    public List<Customer> getListCustomersSearch(String searchText);
    public Customer getCustomerById(int customerId);
}
