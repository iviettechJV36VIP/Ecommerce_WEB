/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.Paypal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public interface PaypalServiceIF {
    public Paypal searchPaypal(int code);
    public Paypal getUpdatePaypal(int newMoney, int id);
    public List<Paypal> getAllPaypal();
}
