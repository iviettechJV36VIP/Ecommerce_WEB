/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.repository;

import com.nhattrung.entity.OrderList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface OrderListRepository extends CrudRepository<OrderList, Integer>{
    
    
    @Query(value = " Select * from orderlist where orderDate like ?1 ",
            nativeQuery = true)
    List<OrderList> findOrderListByOrderDateLike(String orderDate);
    
    @Query(value = " select * from orderlist where Customer_customerId = ?1 ",
            nativeQuery = true)
    List<OrderList> findOrderListByCustomerId(int customerId);
}
