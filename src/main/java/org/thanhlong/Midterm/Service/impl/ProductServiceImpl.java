package org.thanhlong.Midterm.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.DTO.ProductDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Models.Color;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Repository.ProductRepository;
import org.thanhlong.Midterm.Service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> searchByManyCondition(String category, String name, String brand, int minPrice, int maxPrice, String color) {
        List<Object[]> temp = productRepository.searchByManyCondition(category, name, brand, color, minPrice, maxPrice);
        List<Product> products = new ArrayList<>();

        for (Object[] obj : temp) {
            Product product = new Product();
            product.setId((Long) obj[0]);          // id
            product.setName((String) obj[1]);      // name
            Brand searchBrand = new Brand();
            searchBrand.setName((String)obj[2]);
            product.setBrand(searchBrand); // brand
            Color searchColor = new Color();
            searchColor.setName((String) obj[3]);
            product.setColor(searchColor);      // color
            product.setPrice((long) obj[4]);

            product.setDescription((String)obj[5]); //description
            product.setPicture((String) obj[6]);// picture
            products.add(product);

        }
        return products;
    }



    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        products.forEach(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setPicture(product.getPicture());
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());

            // Null checks for associated entities
            if (product.getColor() != null) {
                dto.setColor(product.getColor().getName());
            } else {
                dto.setColor("N/A");
            }

            dto.setPrice(product.getPrice());

            if (product.getCategory() != null) {
                dto.setCategory(product.getCategory().getName());
            } else {
                dto.setCategory("N/A");
            }

            if (product.getBrand() != null) {
                dto.setBrand(product.getBrand().getName());
            } else {
                dto.setBrand("N/A");
            }

            productDTOS.add(dto);
        });

        return productDTOS;
    }


    @Override
	public void addOrUpdateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public boolean removeProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }//delete dua vao pri-key

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProductByCategoryId(Long id) {
        return productRepository.findAllByCategoryId(id);
    }

}
