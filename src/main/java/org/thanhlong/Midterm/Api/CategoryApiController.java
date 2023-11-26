package org.thanhlong.Midterm.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thanhlong.Midterm.DTO.CategoryDTO;
import org.thanhlong.Midterm.Service.impl.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryApiController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategory();
        return categories;
    }

}