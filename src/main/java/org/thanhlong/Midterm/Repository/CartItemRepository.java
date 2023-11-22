
package org.thanhlong.Midterm.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thanhlong.Midterm.Models.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
        @Query("SELECT ci.id,ci.product,ci.quantity FROM CartItem ci WHERE ci.cart.id = :cartId")
        List<Object[]> findByCartId(@Param("cartId") Long cartId);

}