package org.thanhlong.Midterm.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thanhlong.Midterm.Models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT pc.category.name FROM ProductCategory pc WHERE pc.product.id = :productId")
    List<String> findCategoryByProduct(@Param("productId") Long productId);
}
