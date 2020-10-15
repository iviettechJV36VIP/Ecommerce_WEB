/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import java.util.List;
import com.nhattrung.entity.Product;
import com.nhattrung.repository.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class ProductService implements ProductServiceIF{
    
    @Autowired
    ProductRepository productRepository;


   @Override
    public List<Product> getListProductByNoteIsHot(String note) {
       return (List) productRepository.findByNote(note);
    }
   @Override
    public List<Product> getListProductByNoteIsNew(int num) {
       return (List) productRepository.getByNoteNew(num);
    }

    @Override
    public Product getProductDetails(int productId) {
        return  productRepository.findById(productId);
    }

    @Override
    public List<Product> getListProductByCategoryId(int category) {
        return  (List) productRepository.findListProductByCategoryId(category);
    }

    @Override
    public List<Product> getAllProduct() {
        return (List) productRepository.findAll();
    }

    @Override
    public List<Product> getListProductByProducer(int producer) {
        return  (List) productRepository.findListProductByProducer(producer);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepository.search(keyword);
    }

}
