/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.repository;

import com.nhattrung.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByNote(String note);

    @Query(value = "select * from product where productName like ?1 or price like ?2 or dateAdded like ?3 or note like ?4",
            nativeQuery = true)
    List<Product> findListByProductNameLikeOrPriceLikeOrDateAddedOrNoteLike(String productName, String price, String dateAddedd, String note);

    Product findByProductId(int productId);

    @Query(value = "select * from  product where (CURDATE() - dateAdded)<=?1", nativeQuery = true)
    List<Product> getByNoteNew(int num);

    @Query(value = "select * from product where Category_categoryId =?1", nativeQuery = true)
    List<Product> findListProductByCategoryId(int category);

    @Query(value = "select * from product where Producer_producerId=?1", nativeQuery = true)
    List<Product> findListProductByProducer(int producer);

    @Query(value = "select * from product where productName like %?1% or price like %?1%", nativeQuery = true)
    List<Product> search(String keyword);

    @Query(value = "select * from product where productId in (select Product_productId from orderdetails "
            + "group by Product_productId "
            + "order by sum(quantity) DESC)", nativeQuery = true)
    List<Product> searchBestSell();
}
