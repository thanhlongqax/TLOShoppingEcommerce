package org.thanhlong.Midterm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thanhlong.Midterm.Models.Product;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p.id, p.name, p.brand.name, p.color.name, p.price, p.description, p.picture FROM Product p " +
            "WHERE p.id IN (SELECT pc.product.id FROM ProductCategory pc WHERE pc.category.id IN " +
            "(SELECT c.id FROM Category c WHERE (:category IS NULL OR c.name LIKE :category))) " +
            "AND (:color IS NULL OR p.color.name LIKE :color) " +
            "AND (:brand IS NULL OR p.brand.name LIKE :brand) " +
            "AND (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Object[]> searchByManyCondition(@Param("category") String category,
                                         @Param("name") String name,
                                         @Param("brand") String brand,
                                         @Param("color") String color,
                                         @Param("minPrice") Long minPrice,
                                         @Param("maxPrice") Long maxPrice);

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :categoryId")
    List<Product> findAllByCategoryId(@Param("categoryId") Long categoryId);
}