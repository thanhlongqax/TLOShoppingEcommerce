package org.thanhlong.Midterm.Service;



import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.DTO.CategoryDTO;
import org.thanhlong.Midterm.Models.Category;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    List<CategoryDTO> getAllCategory();
    void addCategory(Category category);

    void updateCategory(Category category);

    void removeCategoryById(Long id);

    Optional<Category> getCategoryById(Long id);

    List<String> findCategoryByProductId(Long id);

}
