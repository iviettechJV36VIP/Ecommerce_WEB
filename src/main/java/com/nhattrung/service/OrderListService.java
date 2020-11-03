/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.OrderList;
import com.nhattrung.repository.OrderListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class OrderListService implements OrderListServiceIF{

    @Autowired
    private OrderListRepository orderListRepository;
    
    
    @Override
    public OrderList save(OrderList orderList) {
        return orderListRepository.save(orderList);
    }

    @Override
    public OrderList findId(int orderId) {
        return orderListRepository.findById(orderId).get();
    }

    

    @Override
    public List<OrderList> getOrderByOrderLike(String date) {
        date = "%" +date+ "%";
        return orderListRepository.findOrderListByOrderDateLike(date);
    }
    
}
