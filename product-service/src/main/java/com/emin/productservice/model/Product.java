package com.emin.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    @NotNull
    private String productName;

    @Column (name = "price")
    @NotNull
    private BigDecimal price;

    @Column (name = "description")
    private String description;

    @Column (name = "category")
    @NotNull
    private String category;

    @Column (name = "availability")
    @NotNull
    private int availability;

    public Product() {

    }

    public Product(String productName, BigDecimal price, String description, String category, int availability) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.category = category;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

}