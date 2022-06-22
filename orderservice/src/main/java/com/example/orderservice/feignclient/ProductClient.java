//package com.example.orderservice.feignclient;
//
//
//import com.example.orderservice.model.Product;
//import org.springframework.cloud.netflix.feign.FeignClient;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
////@FeignClient(name = "product", url = "http://localhost:6861/")
//public interface ProductClient {
//
//    @GetMapping(value = "/products/{id}")
//    public Product getProductById(@PathVariable(value = "id") Long productId);
//
//}
