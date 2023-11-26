package org.thanhlong.Midterm.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.thanhlong.Midterm.DTO.CartDTO;
import org.thanhlong.Midterm.DTO.CartItemDTO;
import org.thanhlong.Midterm.Models.Cart;
import org.thanhlong.Midterm.Models.CartItem;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Service.impl.CartItemServiceImpl;
import org.thanhlong.Midterm.Service.impl.CartServiceImpl;
import org.thanhlong.Midterm.Service.impl.ProductServiceImpl;
import org.thanhlong.Midterm.Service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:8080/", methods = {RequestMethod.POST})
@RequestMapping("/api/cart")
public class CartApiController {
    private final CartItemServiceImpl cartItemService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    public CartApiController(CartItemServiceImpl cartItemService) {
        this.cartItemService = cartItemService;
    }
    @PostMapping(value = "/save" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCartToDatabase(@RequestBody List<CartItemDTO> cartItemDTO)  throws Exception {
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getUserByUserName(userName).get();
            CartDTO cartDTO = new CartDTO();
            cartDTO.setUserId(user.getId());
            cartService.addCart(cartDTO);
            cartItemService.saveOrUpdateCartItem(cartItemDTO);
            return ResponseEntity.ok("Cart saved successfully");
        } catch (Exception e) {
            System.err.println("Error saving cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving cart");
        }

    }
}
