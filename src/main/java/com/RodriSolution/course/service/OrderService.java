package com.RodriSolution.course.service;

import com.RodriSolution.course.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.course.mapper.OrderMapper;
import com.RodriSolution.course.model.dtos.OrderResponseDto;
import com.RodriSolution.course.model.entities.Order;
import com.RodriSolution.course.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    public List<OrderResponseDto> OrdersAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());

    }

    @Transactional
    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontrado("Pedido com ID " + id + " não encontrado."));
        return orderMapper.toDto(order);

    }
}
