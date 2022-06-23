package com.example.orderservice.innerClients;

import com.example.orderservice.model.Product;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductClient {
    @Autowired
    EurekaClient eurekaClient;


    public Product getProductById(Long id){
        HashMap<String, Long> params = new HashMap<>();
        params.put("productID", id);
        try {

            ResponseEntity<Product> response
                    = new RestTemplate().getForEntity(
                    getProductServiceUrl()+id,
                    Product.class, params);
            Product product = response.getBody();
            return product;
        }
        catch (Exception ex) {
            throw ex;
        }

    }

    private String getProductServiceUrl(){
        try {
            InstanceInfo instanceInfo =eurekaClient.getApplication("PRODUCT-SERVICE").getInstances().get(0);
            return "http://localhost:"+instanceInfo.getPort()+"/product/products/";
        }catch (Exception exception){
            Logger.getLogger(ProductClient.class.getName()).log(Level.SEVERE,"Product service may not be running");
            throw exception;

        }


    }
}
