package org.thanhlong.Midterm.Service;


import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.DTO.ProductDTO;
import org.thanhlong.Midterm.Models.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

	List<Product> getAllProductByCategoryId(Long id);

	Optional<Product> getProductById(Long id);

	boolean removeProductById(Long id);

	Product addOrUpdateProduct(Product product);

	List<ProductDTO> getAllProduct();

	List<Product> searchByManyCondition(String category, String name,String brand,int minPrice,int maxPrice,String color);

}
