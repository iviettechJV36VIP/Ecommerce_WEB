/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.controller;

import com.nhattrung.config.DailyExcelExporter;
import com.nhattrung.entity.OrderDetails;

import com.nhattrung.entity.OrderList;
import com.nhattrung.repository.OrderDetailsRepository;

import com.nhattrung.repository.OrderListRepository;
import com.nhattrung.service.OrderDetailsService;
import com.nhattrung.service.OrderListService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/")

public class OrderController {

    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    OrderListService orderListService;
    
    @Autowired
    OrderDetailsService orderDetailsService;
    
    @Autowired
    OrderDetailsRepository  orderDetailsRepository;

    @GetMapping(value = {"", "/showOrder", "/showOrder/{page}"})
    public String listAllProducts(@PathVariable(required = false, name = "page") String page, HttpServletRequest request, HttpServletResponse response, Model model) {

        PagedListHolder<OrderList> orderList;
        if (page == null) {
            orderList = new PagedListHolder<OrderList>();
            List<OrderList> orders = (List) orderListRepository.findAll();
            orderList.setSource(orders);
            orderList.setPageSize(10);

            request.getSession().setAttribute("orderList", orderList);
        } else if (page.equals("prev")) {

            orderList = (PagedListHolder<OrderList>) request.getSession().getAttribute("orderList");

            orderList.previousPage();
        } else if (page.equals("next")) {
            orderList = (PagedListHolder<OrderList>) request.getSession().getAttribute("orderList");

            orderList.nextPage();
        } else {
            int pageNum = Integer.parseInt(page);
            orderList = (PagedListHolder<OrderList>) request.getSession().getAttribute("orderList");

            orderList.setPage(pageNum - 1);
        }
        model.addAttribute("orderList", orderList);
        return "staff/staff";
    }

    @RequestMapping(value ={ "/dailyReport","/dailyReport/{type}"}, method = RequestMethod.GET)
    public String dailyReport(@RequestParam(value = "date", required = false) String date,@PathVariable(value = "type",required = false) String type, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<OrderList> orderLists = null ;
        if(type == null){
        orderLists = orderListService.getOrderByOrderLike(date);
        request.getSession().setAttribute("orderLists", orderLists);
        }else if(type.equals("excel")){
            orderLists =  (List<OrderList>) request.getSession().getAttribute("orderLists");
   
        response.setContentType("application/octet-stream");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Print At " + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);      
        DailyExcelExporter excelExporter = new DailyExcelExporter(orderLists);         
        excelExporter.export(response);    
        }
        model.addAttribute("orderLists", orderLists);
        return "staff/showPageReport";
        
    }

    @RequestMapping(method = RequestMethod.GET, value = "/showPageReport")
    public String showPageReport() {
        return "staff/showPageReport";
    }

    @GetMapping(value = "/viewOrderDetails/{orderId}")
    public String viewOrderDetails(@PathVariable ("orderId") int orderId, Model model){
        List<OrderDetails> orderDetailses = orderDetailsRepository.findListOrderDetailsByOrderId(orderId);
        model.addAttribute("orderDetailses", orderDetailses);
        return "staff/viewOrderDetails";
    }

}
