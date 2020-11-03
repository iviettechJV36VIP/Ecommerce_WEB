/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "paypal")
public class Paypal implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paypalId")
    private int paypalId;
    @Column(name = "paypalCode")
    private int paypalCode;
    @Column(name = "paypalName")
    private String paypalName;
    @Column(name = "paypalMonth")
    private int paypalMonth;
    @Column(name = "paypalYear")
    private int paypalYear;
    @Column(name = "paypalSecurityCode")
    private int paypalSecurityCode;
    @Column(name = "paypalMoney")
    private int paypalMoney;
    
    
    @OneToMany(mappedBy = "paypal")
    private List<Payment> payments;

    public Paypal() {
    }

    public Paypal(int paypalId, int paypalCode, String paypalName, int paypalMonth, int paypalYear, int paypalSecurityCode, int paypalMoney, List<Payment> payments) {
        this.paypalId = paypalId;
        this.paypalCode = paypalCode;
        this.paypalName = paypalName;
        this.paypalMonth = paypalMonth;
        this.paypalYear = paypalYear;
        this.paypalSecurityCode = paypalSecurityCode;
        this.paypalMoney = paypalMoney;
        this.payments = payments;
    }

    public int getPaypalId() {
        return paypalId;
    }

    public void setPaypalId(int paypalId) {
        this.paypalId = paypalId;
    }

    public int getPaypalCode() {
        return paypalCode;
    }

    public void setPaypalCode(int paypalCode) {
        this.paypalCode = paypalCode;
    }

    public String getPaypalName() {
        return paypalName;
    }

    public void setPaypalName(String paypalName) {
        this.paypalName = paypalName;
    }

    public int getPaypalMonth() {
        return paypalMonth;
    }

    public void setPaypalMonth(int paypalMonth) {
        this.paypalMonth = paypalMonth;
    }

    public int getPaypalYear() {
        return paypalYear;
    }

    public void setPaypalYear(int paypalYear) {
        this.paypalYear = paypalYear;
    }

    public int getPaypalSecurityCode() {
        return paypalSecurityCode;
    }

    public void setPaypalSecurityCode(int paypalSecurityCode) {
        this.paypalSecurityCode = paypalSecurityCode;
    }

    public int getPaypalMoney() {
        return paypalMoney;
    }

    public void setPaypalMoney(int paypalMoney) {
        this.paypalMoney = paypalMoney;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    
}
