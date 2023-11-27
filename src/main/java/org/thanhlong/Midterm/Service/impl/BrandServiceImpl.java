package org.thanhlong.Midterm.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.DTO.BrandDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Repository.BrandRepository;
import org.thanhlong.Midterm.Service.BrandService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    private final ProductServiceImpl productService;

    @Autowired
    public BrandServiceImpl(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @Override
    public List<BrandDTO> getAllBrand() {
        List<BrandDTO> brandDTOS = new ArrayList<>();
        List<Brand> brands = brandRepository.findAll();

        brands.forEach(brand -> {
            if (brand.getName() != null) {
                BrandDTO brandDTO = new BrandDTO();
                brandDTO.setId(brand.getId());
                brandDTO.setName(brand.getName());
                brandDTOS.add(brandDTO);
            }
        });

        return brandDTOS;
    }

    @Override
    public Brand getBrandById(Long id) {
        // Lấy thông tin thương hiệu từ cơ sở dữ liệu theo ID
        return brandRepository.findById(id).get();
    }
    @Override
    public void addBrand(Brand brand){
        brandRepository.save(brand);
    }
    @Override
    public void updateBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);

        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();

            // Set the 'brand' field to null for associated products
            Set<Product> products = brand.getProducts();
            for (Product product : products) {
                product.setBrand(null);
                productService.addOrUpdateProduct(product); // Assuming you have a saveProduct method in your productService
            }

            // Now delete the Brand
            brandRepository.deleteById(id);
        }
    }

}
