//package com.example.orderservice.feignclient;
//
//import com.example.orderservice.model.User;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.cloud.starter.feign.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
////@FeignClient(name = "user", url = "http://localhost:6862/")
//
//public interface UserClient {
//
//    @GetMapping(value = "/users/{id}")
//    public User getUserById(@PathVariable("id") Long id);
//}
