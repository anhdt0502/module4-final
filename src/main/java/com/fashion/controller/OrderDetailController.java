package com.fashion.controller;

import com.fashion.entity.OrderDetail;
import com.fashion.service.OrderDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public OrderDetail create(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.create(orderDetail);
    }

    @GetMapping
    public List<OrderDetail> findAll() {
        return orderDetailService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDetail findById(@PathVariable Long id) {
        return orderDetailService.findById(id);
    }

    @PutMapping("/{id}")
    public OrderDetail update(
            @PathVariable Long id,
            @RequestBody OrderDetail orderDetail) {

        return orderDetailService.update(id, orderDetail);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderDetailService.delete(id);
    }
}