package com.RodriSolution.course.model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {


    private Integer quantity;
    private Double  price;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, Double price) {
        this.quantity = quantity;
        this.price = price;
    }



}
