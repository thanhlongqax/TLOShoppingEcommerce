package org.thanhlong.Midterm.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.DTO.CartDTO;
import org.thanhlong.Midterm.Models.Cart;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Repository.CartRepository;
import org.thanhlong.Midterm.Service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserServiceImpl userService;

    @Override
    public Optional<CartDTO> getCartDTOById(Long id) {
        return cartRepository.findById(id).map(cart -> {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cart.getId());
            cartDTO.setUserId(cart.getUser().getId());
            return cartDTO;
        });
    }
    @Override
    public Optional<Cart> getCartById(Long id){
        return cartRepository.findById(id);
    }

    @Override
    public boolean deleteCartById(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            cartRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveOrUpdateCart(List<CartDTO> cartDTO) {
        List<Cart> cart = new ArrayList<>();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName).get();
        cartDTO.forEach(item ->{
            Cart cart1 = new Cart();
            cart1.setUser(user);
            cart.add(cart1);
        });

        cartRepository.saveAll(cart);
    }
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart cart : carts) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cart.getId());
            cartDTO.setUserId(cart.getUser().getId());
            cartDTOs.add(cartDTO);
        }
        return cartDTOs;
    }

    @Override
    public List<CartDTO> getCartByUserId(Long userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart cart : carts) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cart.getId());
            cartDTO.setUserId(cart.getUser().getId());
            cartDTOs.add(cartDTO);
        }
        return cartDTOs;
    }
}
