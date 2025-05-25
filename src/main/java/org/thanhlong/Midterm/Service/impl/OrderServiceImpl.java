package org.thanhlong.Midterm.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.DTO.OrderDTO;
import org.thanhlong.Midterm.Models.Order;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Repository.OrderRepository;
import org.thanhlong.Midterm.Service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserServiceImpl userService;

    public List<OrderDTO> getAllOrder(){
        List<Order>  orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(item ->{
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(item.getId());
            orderDTO.setTotal(item.getOrder_total());
            orderDTO.setOrder_status(item.getOrder_status());
            orderDTO.setFullName(item.getUserOrder() != null ? item.getUserOrder().getName() : "N/A");
            orderDTO.setOrderCreated(item.getCreateDate() != null ? item.getCreateDate() : LocalDateTime.now());
            orderDTO.setPhoneNumber(item.getUserOrder() != null ? item.getUserOrder().getPhoneNumber() : "N/A");
            orderDTO.setUserID(item.getUserOrder() != null ? item.getUserOrder().getId() : -1L); // Điều chỉnh kiểu dữ liệu tùy thuộc vào kiểu của user.getId()
            orderDTO.setEmail(item.getUserOrder() != null ? item.getUserOrder().getEmail() : "N/A");
            orderDTOS.add(orderDTO);

        });
        return orderDTOS;
    }
    public void addOrderByOrderDTO(OrderDTO orderDTO){
        Order order = new Order();
        User user = userService.getUserById(orderDTO.getUserID()).get() ;
        order.setUserOrder(user);
        order.setCreateDate(orderDTO.getOrderCreated());
        order.setOrder_total(orderDTO.getTotal());
        order.setOrder_status("Chưa Giao Hàng");
        orderRepository.save(order);
    }
    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }
    public void updateOrderById(Long id){
        Order order = orderRepository.findById(id).get();
        order.setOrder_status("Đang Giao Hàng");
        orderRepository.save(order);
    }
}
