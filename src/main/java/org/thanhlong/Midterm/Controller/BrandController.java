package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thanhlong.Midterm.DTO.BrandDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Service.impl.BrandServiceImpl;

import java.util.List;

@Controller
public class BrandController {
    private final BrandServiceImpl brandService;

    @Autowired
    public BrandController(BrandServiceImpl brandService){this.brandService = brandService;}

    // Hiển thị danh sách thương hiệu
    @GetMapping(value = {"/Admin/Brand"})
    public String Brands(Model model) {
        List<BrandDTO> brands = brandService.getAllBrand();
        model.addAttribute("brands", brands);
        return "/Admin/Brand_Admin"; // Đặt tên template hiển thị danh sách thương hiệu
    }

    // Hiển thị form thêm thương hiệu
    @GetMapping(value = {"/Admin/Brand/Add"})
    public String addBrand_Admin(Model model) {
        model.addAttribute("brand", new Brand());
        return "/Admin/AddBrand_Admin"; // Đặt tên template hiển thị form thêm thương hiệu
    }

    @PostMapping(value = {"/Admin/Brand/Add"})
    public String addBrand_Admin(@ModelAttribute("brand") Brand brand) {
        brandService.addBrand(brand);
        return "redirect:/Admin/Brand";
    }
    @GetMapping("/editBrand/{id}")
    public ModelAndView editBrand_Admin(@PathVariable("id") Long id, Model model) {
        ModelAndView brandView = new ModelAndView("/Admin/EditBrand_Admin");
        Brand brand = brandService.getBrandById(id);
        model.addAttribute("brand", brand);
        return brandView;
    }
    @PostMapping("/editBrand/{id}")
    public String editBrand_Admin(@PathVariable Long id, @ModelAttribute("brand") Brand updatedBrand) {
        brandService.updateBrand(updatedBrand);
        return "redirect:/Admin/Brand";
    }

    @GetMapping("/deleteBrand/{id}")
    public String deleteBrand_Admin(@PathVariable Long id) {
        brandService.deleteBrandById(id);
        return "redirect:/Admin/Brand";
    }
}
