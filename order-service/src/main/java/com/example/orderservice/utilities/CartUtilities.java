package com.example.orderservice.utilities;

import java.math.BigDecimal;

import com.example.orderservice.model.Product;

public class CartUtilities {

    public static BigDecimal getSubTotalForItem(Product product, int quantity){
       return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
