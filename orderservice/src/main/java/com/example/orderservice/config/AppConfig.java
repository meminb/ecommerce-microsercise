package com.example.orderservice.config;

import com.example.orderservice.innerClients.ProductClient;
import com.example.orderservice.innerClients.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ProductClient productClient() {
        return new ProductClient();
    }
    @Bean
    public UserClient userClient() {
        return new UserClient();
    }
}
