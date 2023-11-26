package org.thanhlong.Midterm.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.DTO.CategoryDTO;
import org.thanhlong.Midterm.Models.Category;
import org.thanhlong.Midterm.Repository.CategoryRepository;
import org.thanhlong.Midterm.Service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        categories.forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            // Set other properties if needed
            categoryDTOS.add(categoryDTO);
        });

        return categoryDTOS;
    }
    @Override
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    @Override
    public void updateCategory(Category category){
    	categoryRepository.save(category);
    }
    @Override
    public void removeCategoryById(Long id){
        categoryRepository.deleteById(id);
    }//delete truyen vao pri-key
    @Override
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }
    @Override
    public List<String> findCategoryByProductId(Long id){
        return categoryRepository.findCategoryByProduct(id);
    }
}
