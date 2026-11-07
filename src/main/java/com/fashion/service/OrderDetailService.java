package com.fashion.service;

import com.fashion.entity.OrderDetail;
import com.fashion.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail findById(Long id) {
        return orderDetailRepository.findById(id).orElse(null);
    }

    public OrderDetail update(Long id, OrderDetail orderDetail) {
        OrderDetail existingOrderDetail =
                orderDetailRepository.findById(id).orElse(null);

        if (existingOrderDetail == null) {
            return null;
        }

        existingOrderDetail.setQuantity(orderDetail.getQuantity());
        existingOrderDetail.setPrice(orderDetail.getPrice());
        existingOrderDetail.setOrder(orderDetail.getOrder());
        existingOrderDetail.setProduct(orderDetail.getProduct());

        return orderDetailRepository.save(existingOrderDetail);
    }

    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }
}