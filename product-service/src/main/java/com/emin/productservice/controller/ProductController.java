package com.emin.productservice.controller;

import com.emin.productservice.model.Product;
import com.emin.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("demo")
    public String demo() {
        return "e ananin ami";
        //return productService.queryById(id);
    }


    @GetMapping("{id}")
    public ResponseEntity<Product> queryById(@PathVariable("id") Long id) {
        return productService.queryById(id);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }
}
