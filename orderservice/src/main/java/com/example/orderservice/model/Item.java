package com.example.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table (name = "items")
@EqualsAndHashCode
@Data
@RequiredArgsConstructor
public class Item {
	
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column (name = "quantity",nullable = false)
    private int quantity;

    @Column (name = "subtotal",nullable = false)
    private BigDecimal subTotal;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "product_id")
    private Product product;

    @ManyToMany (mappedBy = "items")
    @JsonIgnore
    private List<Order> orders;

    public Item(Integer quantity, Product product, BigDecimal subTotalForItem) {
        this.quantity = quantity;
        this.product = product;
        this.subTotal = subTotalForItem;
    }
}
