package com.RodriSolution.course.model.entities;

import com.RodriSolution.course.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name= "tb_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;


    public Order() {
    }

    public Order(long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
    }


    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }


    public Double getTotalQuantity() {
        double total = 0;
        for (OrderItem item: items) {
            total += item.getQuantity();
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return id == order.id && Objects.equals(moment, order.moment) && orderStatus == order.orderStatus && Objects.equals(client, order.client) && Objects.equals(items, order.items) && Objects.equals(payment, order.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moment, orderStatus, client, items, payment);
    }
}
