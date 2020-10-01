/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import java.util.List;
import com.nhattrung.entity.Product;
import com.nhattrung.repository.ProductRepository;
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
    
}
