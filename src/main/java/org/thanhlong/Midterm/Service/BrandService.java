package org.thanhlong.Midterm.Service;


import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.DTO.BrandDTO;
import org.thanhlong.Midterm.Models.Brand;

import java.util.List;
@Service
public interface BrandService {
    List<BrandDTO> getAllBrand();
    Brand getBrandById(Long id);
    void addBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrandById(Long id);
}
