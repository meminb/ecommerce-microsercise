package com.example.orderservice.controller;


import com.example.orderservice.http.header.HeaderGenerator;
import com.example.orderservice.model.Item;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.Product;
import com.example.orderservice.service.CartService;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.innerClients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController

@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private HeaderGenerator headerGenerator;
    @PostMapping("/save-order/{userId}")
    public ResponseEntity<Order>  saveOrder(
            @PathVariable("userId") Long userId,
            @RequestHeader(value = "Cookie") String cartId,
            HttpServletRequest request){

        List<Item> cart = cartService.getAllItemsFromCart(cartId);
        if(cart != null && userId != null) {
            Order order = this.createOrder(cart, userId);
            try{
                orderService.saveOrder(order);
                cartService.deleteCart(cartId);
                return new ResponseEntity<Order>(
                        order,
                        headerGenerator.getHeadersForSuccessPostMethod(request, order.getId()),
                        HttpStatus.CREATED);
            }catch (Exception ex){
                ex.printStackTrace();
                return new ResponseEntity<Order>(
                        headerGenerator.getHeadersForError(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/order-history/{userId}")
    public ResponseEntity<List<Order>> getAllOrdersForUser(@PathVariable("userId") Long userId){

        List<Order> orderList= orderService.getAllByUserId(userId);

        if(!orderList.isEmpty()){
            return ResponseEntity.ok().headers(headerGenerator.getHeadersForSuccessGetMethod()).body(orderList);
        }else {
            return ResponseEntity.notFound().build();
        }


    }



    private Order createOrder(List<Item> cart, Long userID) {
        Order order = new Order();
        order.setItems(cart);
        order.setUserId(userID);
        order.setTotal(BigDecimal.ONE);
        order.setOrderedDate(LocalDate.now());
        order.setStatus("PAYMENT_EXPECTED");
        return order;
    }

    private Product createProduct(String productName, BigDecimal price,List<Item> items) {
       Product product = new Product();
       product.setId(1l);
       product.setProductName(productName);
       product.setPrice(price);
       product.setItems(items);
       return product;
    }




}
