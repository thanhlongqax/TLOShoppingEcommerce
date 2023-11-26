package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thanhlong.Midterm.DTO.ProductDTO;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
public class DetailController {
    private final ProductServiceImpl productService;

    @Autowired
    public DetailController(ProductServiceImpl productService ){
        this.productService = productService;

    }
    @GetMapping("/Detail")
    public String ProductDetail_User(@RequestParam Long id, Model model) {
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
        model.addAttribute("product", productDTO);
        return "DetailProduct";
    }
}
