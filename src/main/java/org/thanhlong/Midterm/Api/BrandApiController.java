package org.thanhlong.Midterm.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thanhlong.Midterm.DTO.BrandDTO;
import org.thanhlong.Midterm.Service.impl.BrandServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandApiController {

    private final BrandServiceImpl brandService;

    @Autowired
    public BrandApiController(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<BrandDTO> getAllBrands() {
        List<BrandDTO> brands = brandService.getAllBrand();
        return brands;
    }
}
