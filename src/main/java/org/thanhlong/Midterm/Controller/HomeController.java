package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Service.ProductService;
import org.thanhlong.Midterm.Service.UserService;
import org.thanhlong.Midterm.Service.impl.ProductServiceImpl;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    public HomeController(ProductServiceImpl productService){this.productService = productService;}


    @GetMapping(value = { "/Home" , "/HomePage"})
    public String HomePage() {
        return "HomePage";
    }


    @GetMapping(value = {"/Cart"})
    public String Cart() {
        return "Cart";
    }
    @GetMapping(value = {"/Admin"})
    public String Home_Admin() {
        return "Home_Admin";
    }
    @GetMapping(value = {"/Admin/Order"})
    public String Order_Admin() {
        return "Order_Admin";
    }
    @GetMapping(value = {"/Admin/Category"})
    public String Category_Admin() {
        return "Category_Admin";
    }
    @GetMapping(value = {"/Admin/Product"})
    public String Product_Admin() {
        return "Product_Admin";
    }
}
