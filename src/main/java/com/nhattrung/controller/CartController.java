/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.controller;


import com.nhattrung.entity.Item;
import com.nhattrung.entity.Product;
import com.nhattrung.service.CategoryService;
import com.nhattrung.service.ProducerService;
import com.nhattrung.service.ProductService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/")
public class CartController {

    
    @Autowired 
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProducerService producerService;
    
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String index(HttpSession session, ModelMap modelMap) {
            int countItems = 0;
            double total = 0 ;
            if(session.getAttribute("cart")!=null){
                List<Item> cart = (List<Item>) session.getAttribute("cart");
                countItems = cart.size();
                for(Item item : cart){
                    total += item.getProduct().getPrice() * item.getQuantity();
                }
            }
            modelMap.put("countItems", countItems);
            modelMap.put("total", total);
            return "cart";
	}

	@RequestMapping(value = "/buy/{productId}", method = RequestMethod.GET)
	public String buy(@PathVariable("productId") int productId, HttpSession session) {
		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			cart.add(new Item(productService.getProductDetails(productId), 1));
			session.setAttribute("cart", cart);
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = this.exists(productId, cart);
			if (index == -1) {
				cart.add(new Item(productService.getProductDetails(productId), 1));
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart";
	}

	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.GET)
	public String remove(@PathVariable("productId") int productId, HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = this.exists(productId, cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart";
	}

	private int exists(int productId, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getProductId()== productId) {
				return i;
			}
		}
		return -1;
	}

}