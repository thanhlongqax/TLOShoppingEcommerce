package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thanhlong.Midterm.DTO.ProductDTO;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Service.impl.ProductServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @GetMapping(value = { "/Home" , "/HomePage"})
    public String HomePage() {
        //System.out.printf(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return "HomePage";
    }

    @GetMapping(value = { "/403"})
    public String errorPage() {
        return "403";
    }
    @GetMapping(value = {"/Admin"})
    public String Home_Admin() {
        return "/Admin/Home_Admin";
    }
}
