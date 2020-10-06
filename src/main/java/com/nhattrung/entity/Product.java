/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Entity
@Table(name ="product")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;
    @Column(name = "productName")
    private String productName;
    @Column(name = "price")
    private int price;
    @Column(name = "comment")
    private String comment;
    
    @Column(name = "note")
    private String note;
    
    @Column(name = "dateAdded")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dateAdded;
    
    @ManyToOne
    @JoinColumn(name = "Category_categoryId")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "Producer_producerId")
    private Producer producer;
    
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Image> images;
    
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetailses;

    public Product() {
    }

    public Product(int productId, String productName, int price, String comment, String note, LocalDate dateAdded, Category category, Producer producer, List<Image> images, List<OrderDetails> orderDetailses) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.comment = comment;
        this.note = note;
        this.dateAdded = dateAdded;
        this.category = category;
        this.producer = producer;
        this.images = images;
        this.orderDetailses = orderDetailses;
    }

    

    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<OrderDetails> getOrderDetailses() {
        return orderDetailses;
    }

    public void setOrderDetailses(List<OrderDetails> orderDetailses) {
        this.orderDetailses = orderDetailses;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    
}
