package org.thanhlong.Midterm.Service;


import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.Models.OrderItem;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderItemService  {
    Optional<OrderItem> getOrderItemById(Long id);

    boolean deleteOrderItemById(Long id);

    OrderItem saveOrUpdateOrderItem(OrderItem orderItem);

    List<OrderItem> getAllOrderItems();

}
