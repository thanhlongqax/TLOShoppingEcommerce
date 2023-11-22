package org.thanhlong.Midterm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thanhlong.Midterm.Models.Color;
@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}