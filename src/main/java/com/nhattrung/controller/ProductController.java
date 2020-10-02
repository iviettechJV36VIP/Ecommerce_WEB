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
    
    @GetMapping(value = {"/", "/home"})
    public String listProductNoteIsHot(HttpServletRequest request, Model theModel) {
        List<Product> products1 = productService.getListProductByNoteIsHot("hot");
        PagedListHolder pagedListHolder = new PagedListHolder(products1);
		int page1 = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page1);
		pagedListHolder.setPageSize(6);
	theModel.addAttribute("pagedListHolder", pagedListHolder);
        
        List<Product> products2 = productService.getListProductByNoteIsNew("new");
        PagedListHolder pagedListHolder1 = new PagedListHolder(products2);
		int page2 = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page2);
		pagedListHolder.setPageSize(6);
        theModel.addAttribute("pagedListHolder1", pagedListHolder1 );
                
	return "home";
    }
   /* @GetMapping(value = "/home")
    public String listProductNoteIsNew(HttpServletRequest request, Model theModel) {
        List<Product> products = productService.getListProductByNoteIsHot("new");
        PagedListHolder pagedListHolder1 = new PagedListHolder(products);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder1.setPage(page);
		pagedListHolder1.setPageSize(9);
	theModel.addAttribute("pagedListHolder1", pagedListHolder1);
                
	return "home";
    }*/
}
