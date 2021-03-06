/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;



@Entity
@Table(name ="customer")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private int customerId;
    @Column(name = "customerFirstName")
    private String customerFirstName;
    
    @Column(name = "customerLastName")
    private String customerLastName;
    
    @Column(name = "birthdate")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate birthdate;
    
    @Column(name = "sex")
    private String sex;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "customerAddress")
    private String customerAddress;
    
    @Column(name = "customerCity")
    private String customerCity;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<OrderList> orderLists;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<AccountRole> accountRoles;
    
    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private ConfirmationToken confirmationToken;

    public Customer() {
    }

    public Customer(int customerId, String customerFirstName, String customerLastName, LocalDate birthdate, String sex, String email, String phone, String customerAddress, String customerCity, String username, String password, boolean enabled, List<OrderList> orderLists, List<AccountRole> accountRoles, ConfirmationToken confirmationToken) {
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.birthdate = birthdate;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.orderLists = orderLists;
        this.accountRoles = accountRoles;
        this.confirmationToken = confirmationToken;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public ConfirmationToken getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(ConfirmationToken confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    
}