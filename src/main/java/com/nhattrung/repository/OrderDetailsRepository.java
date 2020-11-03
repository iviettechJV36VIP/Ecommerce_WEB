/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.repository;

import com.nhattrung.entity.OrderDetails;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer>{
    @Query(value = "select * from orderdetails where OrderList_orderId = ?1 ", nativeQuery = true)
    List<OrderDetails> findListOrderDetailsByOrderId(int orderId);
}
