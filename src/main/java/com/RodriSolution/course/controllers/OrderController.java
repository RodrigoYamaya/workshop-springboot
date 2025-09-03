package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.OrderRequestDto;
import com.RodriSolution.course.model.dtos.OrderResponseDto;
import com.RodriSolution.course.model.dtos.UpdateStatusRequestDto;
import com.RodriSolution.course.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/findAll")
    public ResponseEntity <List<OrderResponseDto>> findAll() {
        List<OrderResponseDto> orderList = orderService.OrdersAll();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);

    }
    @GetMapping("/{id}")
    public ResponseEntity <OrderResponseDto> findById(@PathVariable(value = "id") long id) {
        OrderResponseDto orderResponseDto = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

    @Transactional
    @PostMapping("/save")
    public ResponseEntity<OrderResponseDto> save(@RequestBody @Valid OrderRequestDto orderDto) {
        OrderResponseDto orderSave = orderService.saveOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderSave);
    }

    @Transactional
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(
            @PathVariable(value = "id") long id,
            @RequestBody UpdateStatusRequestDto request) {

        OrderResponseDto orderUpdateStatus = orderService.updateStatus(id, request.newStatus());
        return ResponseEntity.status(HttpStatus.OK).body(orderUpdateStatus);
    }
    

}
