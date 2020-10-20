
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="orderlist")
public class OrderList implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;
    
    @Column(name = "orderDate")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate orderDate;
    
    @Column(name = "amount")
    private int amount;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "numberPhone")
    private String numberPhone;
    
    
    @OneToMany(mappedBy = "orderLists",fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetailses;
    
    @ManyToOne
    @JoinColumn(name = "Customer_customerId")
    private Customer customer;

    public OrderList() {
    }

    public OrderList(int orderId, LocalDate orderDate, int amount, String address, String numberPhone, List<OrderDetails> orderDetailses, Customer customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.amount = amount;
        this.address = address;
        this.numberPhone = numberPhone;
        this.orderDetailses = orderDetailses;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }


    public List<OrderDetails> getOrderDetailses() {
        return orderDetailses;
    }

    public void setOrderDetailses(List<OrderDetails> orderDetailses) {
        this.orderDetailses = orderDetailses;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
}
