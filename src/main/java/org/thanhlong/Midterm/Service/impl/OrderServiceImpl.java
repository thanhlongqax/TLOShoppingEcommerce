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
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName).get();
        orders.forEach(item ->{
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setFullName(user.getName() != null ? user.getName() : "N/A");
            orderDTO.setOrderCreated(item.getCreateDate() != null ? item.getCreateDate() : LocalDateTime.now());
            orderDTO.setPhoneNumber(user.getPhoneNumber() != null ? user.getPhoneNumber() : "N/A");
            orderDTO.setUserID(user.getId() != null ? user.getId() : -1L); // Điều chỉnh kiểu dữ liệu tùy thuộc vào kiểu của user.getId()
            orderDTO.setEmail(user.getEmail() != null ? user.getEmail() : "N/A");
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

    }
}
