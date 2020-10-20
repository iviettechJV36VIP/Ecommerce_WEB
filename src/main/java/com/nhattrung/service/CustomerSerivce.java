/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.Customer;
import com.nhattrung.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class CustomerSerivce implements CustomerServiceIF{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public List<Customer> findAllCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer getCustomerDetails() {
        Customer customer = new Customer();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String username = userDetail.getUsername();
        return customerRepository.findByUsername(username);
    }
    
    
    
}
