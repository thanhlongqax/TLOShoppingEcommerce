package org.thanhlong.Midterm.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.Models.Order;
import org.thanhlong.Midterm.Models.OrderItem;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Repository.OrderItemRepository;
import org.thanhlong.Midterm.Service.OrderItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public boolean deleteOrderItemById(Long id) {
        Optional<OrderItem> optionalOrder = orderItemRepository.findById(id);
        if (optionalOrder.isPresent()) {
            orderItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrderItem saveOrUpdateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Long orderId) {
        List<Object[]> orderItemObjects = orderItemRepository.findByOrderId(orderId);
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object[] orderItemObject : orderItemObjects) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId((Long) orderItemObject[0]);
            orderItem.setOrder((Order) orderItemObject[1]);
            orderItem.setProduct((Product) orderItemObject[2]);
            orderItem.setQuantity((Integer) orderItemObject[3]);
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
