package com.example.orderservice.controller;


import com.example.orderservice.http.header.HeaderGenerator;
import com.example.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
	CartService cartService;
    
    @Autowired
    private HeaderGenerator headerGenerator;


    @GetMapping(value = "/show-basket")
    public ResponseEntity<List<Object>> getCart(@RequestHeader(value = "Cookie") String cartId){


        List<Object> carts = cartService.getCart(cartId);

		if(!carts.isEmpty()){
        	return ResponseEntity.ok().headers(headerGenerator.getHeadersForSuccessGetMethod()).body(carts);

        }else{
			return ResponseEntity.notFound().headers(headerGenerator.getHeadersForError()).build();
		}

    }

    @PostMapping(value = "/add-basket", params = {"productId", "quantity"})
    public ResponseEntity<List<Object>> addItemToCart(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            @RequestHeader(value = "Cookie") String cartId,
            HttpServletRequest request) {
        List<Object> cart = cartService.getCart(cartId);
        if(cart != null) {
        	if(cart.isEmpty()){
        		cartService.addItemToCart(cartId, productId, quantity);
        	}else{
        		if(cartService.checkIfItemIsExist(cartId, productId)){
        			cartService.changeItemQuantity(cartId, productId, quantity);
        		}else {
        			cartService.addItemToCart(cartId, productId, quantity);
        		}
        	}
        	return new ResponseEntity<List<Object>>(
        			cart,
        			headerGenerator.getHeadersForSuccessPostMethod(request, Long.parseLong(cartId)),
        			HttpStatus.CREATED);
        }
        return new ResponseEntity<List<Object>>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/remove-item", params = "productId")
    public ResponseEntity<Void> removeItemFromCart(
            @RequestParam("productId") Long productId,
            @RequestHeader(value = "Cookie") String cartId){
    	List<Object> cart = cartService.getCart(cartId);
    	if(cart != null) {
    		cartService.deleteItemFromCart(cartId, productId);
            return ResponseEntity.ok().headers(headerGenerator.getHeadersForSuccessGetMethod()).build();

    	}
        return new ResponseEntity<Void>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }
}
