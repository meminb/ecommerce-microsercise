package com.example.orderservice.service;

import com.example.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    public Order saveOrder(Order order);
    public List<Order> getAllByUserId(Long id);
}
