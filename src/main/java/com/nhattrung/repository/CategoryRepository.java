/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.repository;

import com.nhattrung.entity.Category;
import com.nhattrung.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{
    @Query(value = "select * from category where categoryId =?", nativeQuery = true)
    List<Category> findListProductByCategory(int categoryId);
    
    @Query(value = "select * from category", nativeQuery = true)
    List<Category> findAllProductFromCategory();
    
   
}
