package com.RodriSolution.course.service;

import com.RodriSolution.course.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.course.mapper.OrderMapper;
import com.RodriSolution.course.model.dtos.OrderRequestDto;
import com.RodriSolution.course.model.dtos.OrderResponseDto;
import com.RodriSolution.course.model.entities.Order;
import com.RodriSolution.course.model.entities.OrderItem;
import com.RodriSolution.course.model.entities.Product;
import com.RodriSolution.course.model.entities.User;
import com.RodriSolution.course.model.enums.OrderStatus;
import com.RodriSolution.course.repositories.OrderRepository;
import com.RodriSolution.course.repositories.ProductRepository;
import com.RodriSolution.course.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

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

    public OrderResponseDto saveOrder(OrderRequestDto orderDto) {
        User client = userRepository.findById(orderDto.clientId())
                .orElseThrow(() -> new RecursoNaoEncontrado("Cliente com ID " + orderDto.clientId() + " não encontrado."));

        Order order = new Order();
        order.setClient(client);
        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        orderDto.items().forEach(itemDto ->  {
            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new RecursoNaoEncontrado("..."));
            OrderItem orderItem = new OrderItem(order, product, itemDto.quantity(), product.getPrice());
            order.getItems().add(orderItem);
        });
        Order saveOrder = orderRepository.save(order);
        return orderMapper.toDto(saveOrder);
    }
}
