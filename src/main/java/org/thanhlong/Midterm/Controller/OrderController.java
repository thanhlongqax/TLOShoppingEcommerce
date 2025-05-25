package org.thanhlong.Midterm.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thanhlong.Midterm.DTO.CartItemDTO;
import org.thanhlong.Midterm.DTO.OrderDTO;
import org.thanhlong.Midterm.Service.impl.CartItemServiceImpl;
import org.thanhlong.Midterm.Service.impl.CartServiceImpl;
import org.thanhlong.Midterm.Service.impl.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    private final OrderServiceImpl orderService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    CartItemServiceImpl cartItemService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }
    @GetMapping(value = "/orderPage")
    public String orderPage(@RequestParam("vnp_ResponseCode") String responseCode,Model model, HttpSession session){
        if (responseCode.equals("00")) {
            // Lấy orderDetails từ session
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("orderDetails");
            orderService.addOrderByOrderDTO(orderDTO);
            // Đưa orderDetails vào model để sử dụng trong view
            model.addAttribute("orderDetails", orderDTO);

            return "OrderDetail";
        }else {
            return "OrderDetail";
        }

    }
    @GetMapping(value = {"/Admin/Order"})
    public String Order_Admin(Model model) {
        List<OrderDTO> orders = orderService.getAllOrder();
        model.addAttribute("orders",orders);
        return "/Admin/Order_Admin";
    }
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder_Admin(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return "redirect:/Admin/Order";
    }
    @GetMapping("/editOrder/{id}")
    public String editOrder_Admin(@PathVariable Long id){
        orderService.updateOrderById(id);
        return "redirect:/Admin/Order";
    }

}

