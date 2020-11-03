/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.Paypal;
import com.nhattrung.repository.PaypalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class PaypalService implements PaypalServiceIF{
    
    @Autowired
    PaypalRepository paypalRepository;

    @Override
    public Paypal searchPaypal(int code) {
        return paypalRepository.search(code);
    }

    @Override
    public Paypal getUpdatePaypal(int newMoney, int id) {
        return paypalRepository.update(newMoney, id);
    }

    @Override
    public List<Paypal> getAllPaypal() {
        return (List) paypalRepository.findAll();
    }
    
}
