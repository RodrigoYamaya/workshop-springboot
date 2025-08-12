package com.RodriSolution.course.service;

import com.RodriSolution.course.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.course.model.entities.Order;
import com.RodriSolution.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> OrdersAll() {
        return orderRepository.findAll();

    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontrado("Pedido com ID " + id + " não encontrado."));

    }
}
