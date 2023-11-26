package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thanhlong.Midterm.DTO.BrandDTO;
import org.thanhlong.Midterm.DTO.CategoryDTO;
import org.thanhlong.Midterm.DTO.ColorDTO;
import org.thanhlong.Midterm.DTO.ProductDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Models.Category;
import org.thanhlong.Midterm.Models.Color;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Service.impl.BrandServiceImpl;
import org.thanhlong.Midterm.Service.impl.CategoryServiceImpl;
import org.thanhlong.Midterm.Service.impl.ColorServiceImpl;
import org.thanhlong.Midterm.Service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;
    private final ColorServiceImpl colorService;
    private final BrandServiceImpl brandService;
    @Autowired
    public ProductController(ProductServiceImpl productService , ColorServiceImpl colorService , BrandServiceImpl brandService , CategoryServiceImpl categoryService){
        this.productService = productService;
        this.brandService = brandService;
        this.colorService = colorService;
        this.categoryService = categoryService;
    }
    @GetMapping(value = {"/Admin/Product" , "/Admin/"} )
    public String Product_Admin(Model model) {
        List<ProductDTO> productList = productService.getAllProduct();
        model.addAttribute("products", productList);
        return "/Admin/Product_Admin";
    }
    @GetMapping( value = {"/Admin/Product/Add"})
    public String addProduct_Admin(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategory();
        List<ColorDTO> colors = colorService.getAllColor();
        List<BrandDTO> brands = brandService.getAllBrand();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);
        return "/Admin/AddProduct_Admin";
    }

    @PostMapping( value = {"/Admin/Product/Add"})
    public String addProduct_Admin(@ModelAttribute("product") Product product , @ModelAttribute("image") String image) {
        product.setPicture(image);
        productService.addOrUpdateProduct(product);

        return "redirect:/Admin/Product";
    }
    @GetMapping("/editProduct/{id}")
    public ModelAndView editProduct_Admin(@PathVariable("id") Long id) {
        ModelAndView productView = new ModelAndView("/Admin/EditProduct_Admin");
        Optional<Product> optionalProduct = productService.getProductById(id);
        List<CategoryDTO> categories = categoryService.getAllCategory();
        List<ColorDTO> colors = colorService.getAllColor();
        List<BrandDTO> brands = brandService.getAllBrand();
        Product product = optionalProduct.get();
        productView.addObject("product", product);
        productView.addObject("categories", categories);
        productView.addObject("brands", brands);
        productView.addObject("colors", colors);

        return productView;
    }
    @GetMapping("/detailProduct/{id}")
    public ModelAndView detailProduct_Admin(@PathVariable("id") Long id) {
        ModelAndView productView = new ModelAndView("/Admin/DetailProduct_Admin");
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
        productView.addObject("product", productDTO);
        return productView;
    }
    @PostMapping(value = "/updateProduct/{id}")
    public String updateProduct_Admin(@PathVariable("id") Long id,
                                @ModelAttribute("product")
                                @RequestParam("image")MultipartFile multipartFile,
                                @RequestParam("name") String name,
                                @RequestParam("price") Integer price,
                                @RequestParam("description")String description,
                                @RequestParam("color") Color color,
                                @RequestParam("brand") Brand brand,
                                @RequestParam("category") Category category)
    {

        Optional<Product> optionalProduct = productService.getProductById(id);
        Product product = optionalProduct.get();
        product = optionalProduct.get();
        product.setName(name);
        String picture = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPicture(picture);
        product.setBrand(brand);
        product.setColor(color);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);

        productService.addOrUpdateProduct(product);
        return "redirect:/Admin/Product";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct_Admin(@PathVariable Long id) {
        // Thực hiện logic để xóa sản phẩm từ cơ sở dữ liệu
        productService.removeProductById(id);

        return "redirect:/Admin/Product"; // Chuyển hướng về trang danh sách sản phẩm sau khi xóa
    }
}
