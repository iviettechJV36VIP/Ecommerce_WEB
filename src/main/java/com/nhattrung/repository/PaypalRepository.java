/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.repository;

import com.nhattrung.entity.Paypal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface PaypalRepository extends CrudRepository<Paypal, Integer>{
    @Query(value = "select * from paypal where paypalCode =?1", nativeQuery = true)
    Paypal search(int code);
    
    @Query(value = "update paypal set paypalMoney = ?1 where paypalId = ?2", nativeQuery = true)
    Paypal update(int newMoney, int id);
}
