package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Service.impl.CartItemServiceImpl;
import org.thanhlong.Midterm.Service.impl.UserServiceImpl;

import java.util.Optional;

@Controller
public class CartController {
    private final CartItemServiceImpl cartItemService;
    @Autowired
    UserServiceImpl userService;

    @Autowired
    public CartController(CartItemServiceImpl cartItemService) {
        this.cartItemService = cartItemService;
    }
    @GetMapping(value = {"/Cart"})
    public String Cart(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userService.getUserByUserName(userName);
        Long userId = user.get().getId();
        model.addAttribute("userId",userId);
        return "Cart";
    }
}
