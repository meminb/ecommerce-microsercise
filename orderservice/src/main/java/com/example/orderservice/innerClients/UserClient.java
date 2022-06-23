package com.example.orderservice.innerClients;

import com.example.orderservice.model.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserClient {

    @Autowired
    EurekaClient eurekaClient;

    public User getUserById(Long id)  {

        HashMap<String, Long> params = new HashMap<>();
        params.put("userId", id);
        try {

            ResponseEntity<User> response
                    = new RestTemplate().getForEntity(
                    getUserServiceUrl()+id,
                    User.class, params);
            User user = response.getBody();
            return user;
        }
        catch (Exception ex) {
            throw ex;
        }

    }

    private String getUserServiceUrl(){

        try {
            InstanceInfo instanceInfo =eurekaClient.getApplication("USER-SERVICE").getInstances().get(0);
            return "http://localhost:"+instanceInfo.getPort()+"/users/";
        }
        catch (Exception exception){
            Logger.getLogger(ProductClient.class.getName()).log(Level.SEVERE,"User service may not be running");
            throw exception;

        }

    }

}
