package org.thanhlong.Midterm.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.Models.CartItem;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Repository.CartItemRepository;
import org.thanhlong.Midterm.Service.CartItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

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
            //cartItem.setProduct((Product) cartItemObject[1]);
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
    public void saveOrUpdateCartItem(List<CartItem> cartItems) {
        // Assuming CartItemRepository has a method for saving or updating cart items
        cartItemRepository.saveAll(cartItems);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
}
