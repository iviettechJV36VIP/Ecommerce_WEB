/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.OrderDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public interface OrderDetailsServiceIF {
    public OrderDetails save(OrderDetails orderDetails);
    public OrderDetails findId(int orderDetailsId);
}
