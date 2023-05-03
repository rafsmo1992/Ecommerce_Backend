package org.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.ecommerce.domain.Order;
import org.ecommerce.domain.dto.OrderDto;
import org.ecommerce.mapper.OrderMapper;
import org.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommercee/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> findOrders() {
        List<Order> orderList = orderService.findOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orderList));
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> findOrder (@PathVariable Long orderId) {
        return new ResponseEntity<>(orderMapper.mapToOrderDto(orderService.findOrder(orderId)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addOrder (@RequestBody OrderDto orderDto)  {
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<OrderDto> editOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));

    }


    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}