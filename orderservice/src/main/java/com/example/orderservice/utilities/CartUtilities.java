package com.example.orderservice.utilities;

import com.example.orderservice.model.Product;


import java.math.BigDecimal;

public class CartUtilities {

    public static BigDecimal getSubTotalForItem(Product product, int quantity){
       return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
