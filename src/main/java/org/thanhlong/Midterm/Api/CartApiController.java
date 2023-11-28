package org.thanhlong.Midterm.Api;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thanhlong.Midterm.DTO.CartDTO;
import org.thanhlong.Midterm.DTO.CartItemDTO;
import org.thanhlong.Midterm.DTO.OrderDTO;
import org.thanhlong.Midterm.Models.Cart;
import org.thanhlong.Midterm.Models.CartItem;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Service.impl.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class CartApiController {
    private final CartItemServiceImpl cartItemService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    public CartApiController(CartItemServiceImpl cartItemService) {
        this.cartItemService = cartItemService;
    }
    @PostMapping(value = "/cart/save" , produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveCartToDatabase(@RequestBody List<CartItemDTO> cartItemDTO  , HttpSession session)  throws Exception {
        ModelAndView orderView = new ModelAndView("OrderDetail");
        cartItemService.saveOrUpdateCartItem(cartItemDTO);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName).get();
        int totalPaid = 0;
        int quantity = 0;
        for (CartItemDTO item : cartItemDTO) {
            totalPaid += item.getQuantity() * item.getPrice();
            quantity += item.getQuantity();
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserID(user.getId());
        orderDTO.setOrderCreated(LocalDateTime.now());
        orderDTO.setEmail(user.getEmail() != null ? user.getEmail() : "N/A");
        orderDTO.setTotal(totalPaid);
        orderDTO.setQuantity(quantity);
        orderDTO.setAddress(user.getAddress());
        orderDTO.setFullName(user.getName());
        orderDTO.setPhoneNumber(user.getPhoneNumber());
        session.setAttribute("orderDetails", orderDTO);
        orderService.addOrderByOrderDTO(orderDTO);
        return "";
    }

//    @GetMapping("/cartItems")
//    public List<CartItemDTO> getAllCartItems() {
//        return cartItemService.getAllCartItems();
//    }

    @GetMapping("/carts")
    public List<CartDTO> getAllCarts() {
        return cartService.getAllCarts();
    }
}
