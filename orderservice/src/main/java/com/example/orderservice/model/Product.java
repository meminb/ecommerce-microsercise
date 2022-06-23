package com.example.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table (name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long productId;

    @Transient
    private Long id;

    @Column (name = "product_name",nullable = false)
    private String productName;
    @Column (name = "price",nullable = false)
    private BigDecimal price;

    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Item> items;
}
