/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.OrderDetails;
import com.nhattrung.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class OrderDetailsService implements OrderDetailsServiceIF{
    
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    public OrderDetails findId(int orderDetailsId) {
        return orderDetailsRepository.findById(orderDetailsId).get();
    }
    
}
