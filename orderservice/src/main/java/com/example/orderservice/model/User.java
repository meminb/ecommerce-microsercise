package com.example.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "user_name")
    private String userName;

    @JsonIgnore
    private List<Order> orders;


}
