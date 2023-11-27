package org.thanhlong.Midterm.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.DTO.CartItemDTO;
import org.thanhlong.Midterm.Models.Cart;
import org.thanhlong.Midterm.Models.CartItem;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Repository.CartItemRepository;
import org.thanhlong.Midterm.Repository.UserRepository;
import org.thanhlong.Midterm.Service.CartItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public List<CartItem> getCartItemByCartId(Long cartId) {
        List<Object[]> cartItemObjects = cartItemRepository.findByCartId(cartId);
        List<CartItem> cartItems = new ArrayList<>();
        for (Object[] cartItemObject : cartItemObjects) {
            CartItem cartItem = new CartItem();
            cartItem.setId((Long) cartItemObject[0]);
            cartItem.setProduct((Product) cartItemObject[1]);
            cartItem.setQuantity((Integer) cartItemObject[2]);
            cartItems.add(cartItem);
        }

        return cartItems;
    }

    @Override
    public boolean deleteCartItemById(Long id) {
        Optional<CartItem> optionalCart = cartItemRepository.findById(id);
        if (optionalCart.isPresent()) {
            cartItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveOrUpdateCartItem(List<CartItemDTO> cartItemDTO) {
        List<CartItem> cartItems = new ArrayList<>();

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName).get();

        cartItemDTO.forEach(item ->{
            CartItem cartItem = new CartItem();
            Cart cart = new Cart();

            cart.setUser(user);
            cartService.addCart(cart);
            cartItem.setCart(cart);

            cartItem.setProduct(productService.getProductById(item.getId()).get());
            cartItem.setQuantity(item.getQuantity());
            cartItems.add(cartItem);
        });
        cartItemRepository.saveAll(cartItems);
    }

//    @Override
//    public List<CartItemDTO> getAllCartItems() {
//        List<CartItem> cartItems = cartItemRepository.findAll();
//        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
//
//
//        cartItems.forEach(item ->{
//            CartItemDTO cartItemDTO = new CartItemDTO();
//            cartItemDTO.setId(item.getId());
//            Product products = productService.getProductById(item.getId()).get()
//            cartItemDTO.set
//        });
//
//    }
}
