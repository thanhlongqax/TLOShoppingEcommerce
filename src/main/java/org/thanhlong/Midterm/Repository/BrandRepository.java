package org.thanhlong.Midterm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thanhlong.Midterm.Models.Brand;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
