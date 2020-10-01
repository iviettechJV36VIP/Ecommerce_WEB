/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.nhattrung.entity.Product;
import com.nhattrung.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */

@Controller
@RequestMapping("/")

public class ProductController {
    
    @Autowired 
    private ProductService productService;
    
    @GetMapping(value = "/home")
    public String listProduct(HttpServletRequest request, Model theModel) {
        List<Product> products = productService.getListProductByNoteIsHot("hot");
        PagedListHolder pagedListHolder = new PagedListHolder(products);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(5);
                
	theModel.addAttribute("pagedListHolder", pagedListHolder);
                
	return "home";
    }
}
