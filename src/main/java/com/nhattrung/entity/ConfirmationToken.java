/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.entity;


import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class ConfirmationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenId")
    private int tokenId;

    @Column(name = "confirmationToken")
    private String confirmationToken;

    

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public ConfirmationToken() {
    }

    public ConfirmationToken(Customer customer) {
        this.customer = customer;
        
        confirmationToken = UUID.randomUUID().toString();
    }

    public ConfirmationToken(int tokenId, String confirmationToken, Customer customer) {
        this.tokenId = tokenId;
        this.confirmationToken = confirmationToken;
        this.customer = customer;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    

    

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
}
