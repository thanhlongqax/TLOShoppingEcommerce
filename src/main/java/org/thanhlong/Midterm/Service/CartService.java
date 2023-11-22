package org.thanhlong.Midterm.Service;


import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.DTO.CartDTO;
import org.thanhlong.Midterm.Models.Cart;

import java.util.List;
import java.util.Optional;

@Service
public interface CartService {
    Optional<CartDTO> getCartDTOById(Long id);

    Optional<Cart> getCartById(Long id);

    boolean deleteCartById(Long id);

    Cart saveOrUpdateCart(Cart cart);

    List<CartDTO> getAllCarts();

    List<CartDTO> getCartByUserId(Long userId);
}
