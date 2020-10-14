/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.nhattrung.entity.Product;
import com.nhattrung.service.CategoryService;
import com.nhattrung.service.ProducerService;
import com.nhattrung.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrator
 */

@Controller

@RequestMapping("/")
    

public class ProductController {
    
    @Autowired 
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProducerService producerService;
    
    @GetMapping(value = {"/", "/home"})
    public String listProduct(HttpServletRequest request, Model theModel, ModelMap mm) {
        List<Product> products1 = productService.getListProductByNoteIsHot("hot");
        PagedListHolder pagedListHolder1 = new PagedListHolder(products1);
		int page1 = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder1.setPage(page1);
		pagedListHolder1.setPageSize(3);
	theModel.addAttribute("pagedListHolder1", pagedListHolder1);
        
        List<Product> products2 = productService.getListProductByNoteIsNew(30);
        PagedListHolder pagedListHolder2 = new PagedListHolder(products2);
		int page2 = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder2.setPage(page2);
		pagedListHolder2.setPageSize(3);
        theModel.addAttribute("pagedListHolder2", pagedListHolder2 );
        
        mm.put("listCategory", categoryService.getAllProductByCategory());
        mm.put("listProducer", producerService.getAllProductByProducer());        
	return "home";
    }
    
    @GetMapping(value = "/listProduct/productHot")
    public String listProductNoteIsHot(HttpServletRequest request, Model theModel) {
        List<Product> products1 = productService.getListProductByNoteIsHot("hot");
        PagedListHolder pagedListHolder = new PagedListHolder(products1);
		int page1 = ServletRequestUtils.getIntParameter(request, "p", 0);
	theModel.addAttribute("pagedListHolder", pagedListHolder);
                
	return "list-hot-product";
        
    }
    @GetMapping(value = "/listAllProductNew")
    public String listProductNoteIsNew(HttpServletRequest request, Model theModel) {
        List<Product> products1 = productService.getListProductByNoteIsNew(30);
        PagedListHolder pagedListHolder = new PagedListHolder(products1);
		int page1 = ServletRequestUtils.getIntParameter(request, "p", 0);
	theModel.addAttribute("pagedListHolder", pagedListHolder);
                
	return "list-hot-product";
        
    }
   
    @RequestMapping(value = "/productDetails/{productId}", method = RequestMethod.GET)
    public String productDetails(@PathVariable("productId") int productId, ModelMap  modelMap){
        modelMap.put("listCategory", categoryService.getAllProductByCategory());
        modelMap.put("listProducer", producerService.getAllProductByProducer());
        modelMap.put("product", productService.getProductDetails(productId));   
        return "productDetails";
    }
    
    
    @RequestMapping(value = "/category/{categoryName}/{categoryId}", method = RequestMethod.GET)
    public String productListByCategory(@PathVariable("categoryId") int categoryId,@PathVariable("categoryName") String categoryName, ModelMap  modelMap){
        modelMap.put("listCategory", categoryService.getAllProductByCategory());
        modelMap.put("listProducer", producerService.getAllProductByProducer());
        modelMap.put("listByCategoryId", productService.getListProductByCategoryId(categoryId)); 
        return "category";
    }
    
    @RequestMapping(value = "/producer/{producerName}/{producerId}", method = RequestMethod.GET)
    public String productListByProducer(@PathVariable("producerId") int producerId,@PathVariable("producerName") String producerName, ModelMap  modelMap){
        modelMap.put("listProducer", producerService.getAllProductByProducer());
        modelMap.put("listCategory", categoryService.getAllProductByCategory());
        modelMap.put("listByProducer", productService.getListProductByProducer(producerId));  
        return "producer";
    }
    
}
