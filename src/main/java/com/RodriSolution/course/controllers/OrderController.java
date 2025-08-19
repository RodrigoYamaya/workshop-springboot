package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.OrderRequestDto;
import com.RodriSolution.course.model.dtos.OrderResponseDto;
import com.RodriSolution.course.model.dtos.ProductRequestDto;
import com.RodriSolution.course.model.dtos.ProductResponseDto;
import com.RodriSolution.course.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.annotations.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/lista")
    public ResponseEntity <List<OrderResponseDto>> findAll() {
        List<OrderResponseDto> orderList = orderService.OrdersAll();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);

    }
    @GetMapping("/order/{id}")
    public ResponseEntity <OrderResponseDto> findById(@PathVariable(value = "id") long id) {
        OrderResponseDto orderResponseDto = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

    @Transactional
    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> save(@RequestBody @Valid OrderRequestDto orderDto) {
        OrderResponseDto orderSave = orderService.saveOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderSave);
    }
}
