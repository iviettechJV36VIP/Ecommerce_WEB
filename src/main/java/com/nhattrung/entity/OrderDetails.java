/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="orderdetails")
public class OrderDetails implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetailsId")
    private int orderDetailsId;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "price")
    private int price;
    
    @ManyToOne
    @JoinColumn(name = "Product_productId")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "OrderList_orderId")
    private OrderList orderLists;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailsId, int quantity, int price, Product product, OrderList orderLists) {
        this.orderDetailsId = orderDetailsId;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.orderLists = orderLists;
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderList getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(OrderList orderLists) {
        this.orderLists = orderLists;
    }

    
    
    
}
