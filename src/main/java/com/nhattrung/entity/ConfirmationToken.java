/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.entity;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenId")
    private long tokenId;

    @Column(name = "confirmationToken")
    private String confirmationToken;

    

    @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "customerId")
    private Customer customer;

    public ConfirmationToken() {
    }

    public ConfirmationToken(Customer customer) {
        this.customer = customer;
        
        confirmationToken = UUID.randomUUID().toString();
    }

    public ConfirmationToken(long tokenId, String confirmationToken, Customer customer) {
        this.tokenId = tokenId;
        this.confirmationToken = confirmationToken;
        this.customer = customer;
    }

    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
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
