package org.thanhlong.Midterm.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.DTO.ProductDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Models.Color;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Repository.ProductRepository;
import org.thanhlong.Midterm.Service.ProductService;

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
            product.setBrand((Brand) obj[2]);      // brand
            product.setColor((Color) obj[3]);      // color
            product.setPrice((int) obj[4]);        // price
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
            dto.setColor(product.getColor().getName());
            dto.setPrice(product.getPrice());
            //dto.setCategory(product.getCategory().getName());
            dto.setBrand(product.getBrand().getName());

            productDTOS.add(dto);
        });
        return productDTOS;
    }//findAll

    @Override
	public Product addOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }//add or update (tuy vao pri-key)

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
