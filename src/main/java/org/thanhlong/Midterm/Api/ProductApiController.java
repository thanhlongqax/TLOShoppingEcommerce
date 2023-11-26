package org.thanhlong.Midterm.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thanhlong.Midterm.DTO.ProductDTO;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductApiController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProduct();
    }
    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam(name = "productName", required = false ,defaultValue = "") String productName,
            @RequestParam(name = "color", required = false , defaultValue = "") String color,
            @RequestParam(name = "category", required = false ,defaultValue = "") String category,
            @RequestParam(name = "brand", required = false ,defaultValue = "") String brand,
            @RequestParam(name = "minPrice", required = false,defaultValue = "0")  long minPrice,
            @RequestParam(name = "maxPrice", required = false,defaultValue = "999999999") long maxPrice) {
        List<Product> filteredProduct = productService.searchByManyCondition(category ,productName , brand, minPrice , maxPrice ,color);
        return filteredProduct;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {

        Optional<Product> optionalProduct = productService.getProductById(id);
        Product product = optionalProduct.get();
        ProductDTO productDTO  = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setBrand(product.getBrand() != null ? product.getBrand().getName() : "N/A");
        productDTO.setColor(product.getColor() != null ? product.getColor().getName() : "N/A");
        productDTO.setDescription(product.getDescription() != null ? product.getDescription() : "N/A");
        productDTO.setCategory(product.getCategory() != null ? product.getCategory().getName(): "N/A");
        productDTO.setPicture(product.getPicture());
        productDTO.setPrice(product.getPrice());
        if (productDTO != null) {
            return ResponseEntity.ok().body(productDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}