/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.repository;

import com.nhattrung.entity.Category;
import com.nhattrung.entity.Producer;
import com.nhattrung.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrator
 */
public interface ProducerRepository extends CrudRepository<Producer, Integer>{
    
    @Query(value = "select * from producer", nativeQuery = true)
    List<Producer> findAllProductFromProducer();
    
}
