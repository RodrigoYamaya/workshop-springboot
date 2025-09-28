package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.OrderRequestDto;
import com.RodriSolution.course.model.dtos.OrderResponseDto;
import com.RodriSolution.course.model.dtos.UpdateStatusRequestDto;
import com.RodriSolution.course.security.SecurityConfig;
import com.RodriSolution.course.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos de compra.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Lista todos os pedidos", description = "Retorna uma lista de todos os pedidos de compra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity <List<OrderResponseDto>> findAll() {
        List<OrderResponseDto> orderList = orderService.OrdersAll();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);

    }

    @Operation(summary = "Busca pedido por ID", description = "Retorna um pedido específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity <OrderResponseDto> findById(@PathVariable(value = "id") long id) {
        OrderResponseDto orderResponseDto = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }



    @Operation(summary = "Cria um novo pedido", description = "Salva um novo pedido de compra no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos (ex: IDs de produto ou usuário inválidos)."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @PostMapping("/save")
    public ResponseEntity<OrderResponseDto> save(@RequestBody @Valid OrderRequestDto orderDto) {
        OrderResponseDto orderSave = orderService.saveOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderSave);
    }



    @Operation(summary = "Atualiza o status do pedido", description = "Atualiza o status de um pedido (ex: DE ENVIADO para ENTREGUE).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do pedido atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Status inválido fornecido ou pedido não pode ser atualizado para este status."),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(
            @PathVariable(value = "id") long id,
            @RequestBody UpdateStatusRequestDto request) {

        OrderResponseDto orderUpdateStatus = orderService.updateStatus(id, request.newStatus());
        return ResponseEntity.status(HttpStatus.OK).body(orderUpdateStatus);
    }
    

}
