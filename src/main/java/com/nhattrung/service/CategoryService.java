/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.Category;
import com.nhattrung.entity.Product;
import com.nhattrung.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class CategoryService implements CategoryServiceIF{
    @Autowired
    CategoryRepository categoryRepository;

    

    @Override
    public List<Category> getAllProductByCategory() {
        return categoryRepository.findAllProductFromCategory();
    }

    @Override
    public List<Category> getListProductByCategory(int categoryId) {
        return categoryRepository.findListProductByCategory(categoryId);
    }
}
