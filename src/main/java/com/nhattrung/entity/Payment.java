/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 *
 * @author Administrator
 */
@Entity
@Table (name = "payment")
public class Payment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private int paymentId;

    
    @Column(name = "paymentDate")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate paymentDate;
    
    @Column(name = "amount")
    private int amount;
    
    @ManyToOne
    @JoinColumn(name = "Paypal_paypalId")
    private Paypal paypal;
    
    @ManyToOne
    @JoinColumn(name = "Orderlist_orderId")
    private OrderList orderLists;

    public Payment() {
    }

    public Payment(int paymentId, LocalDate paymentDate, int amount, Paypal paypal, OrderList orderLists) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paypal = paypal;
        this.orderLists = orderLists;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Paypal getPaypal() {
        return paypal;
    }

    public void setPaypal(Paypal paypal) {
        this.paypal = paypal;
    }

    public OrderList getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(OrderList orderLists) {
        this.orderLists = orderLists;
    }

    
    
}
