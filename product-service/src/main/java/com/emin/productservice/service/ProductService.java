package com.emin.productservice.service;

import com.emin.productservice.data.ProductRepository;
import com.emin.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@ Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<Product> queryById(Long id) {
        Product product =
                productRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("There is no product with given Id"));
        return ResponseEntity.ok().body(product);
    }

    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok().body(productRepository.findAll());
    }

    public ResponseEntity<Product> save(Product product) {
        return ResponseEntity.ok().body(productRepository.save(product));
    }
}