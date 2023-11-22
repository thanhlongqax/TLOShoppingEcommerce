package org.thanhlong.Midterm.Service;


import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.Models.CartItem;

import java.util.List;
import java.util.Optional;

@Service
public interface CartItemService {
    Optional<CartItem> getCartItemById(Long id);

    boolean deleteCartItemById(Long id);

    void saveOrUpdateCartItem(List<CartItem> cartItems);

    List<CartItem> getAllCartItems();

    List<CartItem> getCartItemByCartId(Long id);
}
