/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.repository;

import com.nhattrung.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    Customer findByUsername(String username);
    Customer findByEmail(String email);
    
    @Query(value = "select * from customer  where customerFirstName like ?1 or customerLastName like ?2 or sex like ?3 or customerAddress like ?4",
            nativeQuery = true)
    List<Customer> findListByCustomerFirstNameLikeOrCustomerLastNameLikeOrSexLikeOrCustomerAddressLike(String customerFirstName,String customerLastName, String sex, String customerAddress);
    Customer findByCustomerId(int customerId);
}
