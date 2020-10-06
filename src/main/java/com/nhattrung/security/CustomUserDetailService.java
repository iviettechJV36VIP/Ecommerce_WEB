/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.security;

import com.nhattrung.entity.Customer;
import com.nhattrung.repository.CustomerRepository;
import com.nhattrung.service.CustomerServiceIF;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*@Service("customUserService")
public class CustomUserDetailService  implements UserDetailsService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Customer customer = customerRepository.findByUsername(username);
         if(customer ==null){
             throw new UsernameNotFoundException(username);
         }
        UserDetails user = User.withUsername(customer.getUsername())
                                .password(customer.getPassword()).build();
        return user;
        
    }
}*/
