package org.thanhlong.Midterm.Controller;

// CartController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thanhlong.Midterm.Models.CartItem;
import org.thanhlong.Midterm.Service.impl.CartItemServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartItemServiceImpl cartItemService;

    @Autowired
    public CartController(CartItemServiceImpl cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/items")
    public List<CartItem> getCartItems() {
        return cartItemService.getAllCartItems();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCart(@RequestBody List<CartItem> cartItems) {
        try {
            // Lưu giỏ hàng vào CSDL
            cartItemService.saveOrUpdateCartItem(cartItems);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cart saved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving cart: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
