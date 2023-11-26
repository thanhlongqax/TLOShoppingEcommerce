package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thanhlong.Midterm.DTO.CategoryDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Models.Category;
import org.thanhlong.Midterm.Service.impl.BrandServiceImpl;
import org.thanhlong.Midterm.Service.impl.CategoryServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService){this.categoryService = categoryService;}
    @GetMapping(value = {"/Admin/Category"})
    public String Category_Admin(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategory();
        model.addAttribute("categories" , categories);
        return "/Admin/Category_Admin";
    }
    @GetMapping(value = {"/Admin/Category/Add"})
    public String addCategory_Admin(Model model) {
        model.addAttribute("category", new Category());
        return "/Admin/AddCategory_Admin"; // Đặt tên template hiển thị form thêm thương hiệu
    }

    @PostMapping(value = {"/Admin/Category/Add"})
    public String addCategory_Admin(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/Admin/Category";
    }
    @GetMapping("/editCategory/{id}")
    public ModelAndView editCategory_Admin(@PathVariable("id") Long id, Model model) {
        ModelAndView CategoryView = new ModelAndView("/Admin/EditCategory_Admin");
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        Category category = optionalCategory.get();
        model.addAttribute("category", category);
        return CategoryView;
    }
    @PostMapping("/editCategory/{id}")
    public String editBrand_Admin(@PathVariable Long id, @ModelAttribute("catogory") Category updateCategory) {
        categoryService.updateCategory(updateCategory);
        return "redirect:/Admin/Category";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory_Admin(@PathVariable Long id) {
        categoryService.removeCategoryById(id);
        return "redirect:/Admin/Category";
    }
}
