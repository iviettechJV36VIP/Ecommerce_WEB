/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.service;

import com.nhattrung.entity.Producer;
import com.nhattrung.entity.Product;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public interface ProducerServiceIF {
    public List<Producer> getAllProductByProducer();
}
