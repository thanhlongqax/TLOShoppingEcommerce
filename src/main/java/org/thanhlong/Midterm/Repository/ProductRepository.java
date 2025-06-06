package org.thanhlong.Midterm.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thanhlong.Midterm.Models.Product;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p.id, p.name, p.brand.name, p.color.name, p.price, p.description, p.picture,p.category FROM Product p " +
            "WHERE " +
            "(:category IS NULL OR p.category.name LIKE :category) " +
            "AND (:color IS NULL OR p.color.name LIKE :color) " +
            "AND (:brand IS NULL OR p.brand.name LIKE :brand) " +
            "AND (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Object[]> searchByManyCondition(@Param("category") String category,
                                         @Param("name") String name,
                                         @Param("brand") String brand,
                                         @Param("color") String color,
                                         @Param("minPrice") int minPrice,
                                         @Param("maxPrice") int maxPrice);

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :categoryId")
    List<Product> findAllByCategoryId(@Param("categoryId") Long categoryId);
    Page<Product> findAll(Pageable pageable);
}