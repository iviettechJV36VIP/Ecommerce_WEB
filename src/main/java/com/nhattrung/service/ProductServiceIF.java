/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.Product;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public interface ProductServiceIF {
    public List<Product> getListProductByNoteIsHot(String note);
    public List<Product> getListProductByNoteIsNew(int num);
 //   public List<Product> getProducts();
    public Product getProductDetails(int productId);
    public List<Product> getListProductByCategoryId(int category);
    public List<Product> getListProductByProducer(int producer);
    public List<Product> getAllProduct();
    public List<Product> searchProduct(String keyword);
    
    public List<Product> getListProductsSearch(String searchText);
    public Product getProductByProductId(int productId);
    
    public List<Product> getBestSell();
}
