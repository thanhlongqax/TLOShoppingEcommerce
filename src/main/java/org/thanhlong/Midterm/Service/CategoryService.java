package org.thanhlong.Midterm.Service;



import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.Models.Category;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    public List<Category> getAllCategory();

    public void updateCategory(Category category);

    public void removeCategoryById(Long id);

    public Optional<Category> getCategoryById(Long id);

    public List<String> findCategoryByProductId(Long id);

}
