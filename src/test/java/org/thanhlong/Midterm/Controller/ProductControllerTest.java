package org.thanhlong.Midterm.Controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.thanhlong.Midterm.DTO.ProductDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Models.Category;
import org.thanhlong.Midterm.Models.Color;
import org.thanhlong.Midterm.Models.Product;
import org.thanhlong.Midterm.Repository.*;
import org.thanhlong.Midterm.Service.impl.BrandServiceImpl;
import org.thanhlong.Midterm.Service.impl.CategoryServiceImpl;
import org.thanhlong.Midterm.Service.impl.ColorServiceImpl;
import org.thanhlong.Midterm.Service.impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

   @InjectMocks
    private BrandServiceImpl brandService;
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    private ColorServiceImpl colorService;

    @Mock
    private ColorRepository colorRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Áo");
        product.setPrice(200);
        product.setDescription("Đẹp");
        product.setPicture("image1");
        productRepository.save(product);

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));
        Product res = productService.getProductById(1L).get();
        Assert.assertEquals(res, product);
    }

    @Test
    public void getAllProduct() throws Exception {

        //tao color
        Color color = new Color();
        color.setId(1L);
        color.setName("Red");
        colorRepository.save(color);

        //tao brand
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Nike");
        brandRepository.save(brand);
        //tao category
        Category category = new Category();
        category.setId(1L);
        category.setName("Ao");
        categoryRepository.save(category);

        Product product = new Product();
        product.setId(1L);
        product.setName("Quần");
        product.setPrice(30000);
        product.setDescription("Đô dep lam na");
        product.setPicture("piture");;
        product.setCategory(category);
        product.setColor(color);
        product.setBrand(brand);
        productRepository.save(product);
        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("Quan");
        product1.setPrice(300);
        product1.setDescription("Quá Đẹp lắm òi á noa");
        product1.setPicture("piture2");
        product1.setCategory(category);
        product1.setColor(color);
        product1.setBrand(brand);
        productRepository.save(product1);

        List<Product> productList = new ArrayList<>();
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<ProductDTO> res = productService.getAllProduct();
        List<Product> products = new ArrayList<>();
        res.forEach(item -> {
            Product product2 = new Product();
            product2.setId(item.getId());
            product2.setPrice(item.getPrice());
            product2.setDescription(item.getDescription());
            product2.setBrand(brandService.getBrandById(1L));
            product2.setColor(colorService.getColorById(1L));
            product2.setCategory(categoryService.getCategoryById(1L).get());
            product2.setName(item.getName());
            products.add(product2);
        });

        Assert.assertEquals(products, productList);
    }
    @Test
    public void deleteProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Áo KIMOSA");
        product.setPrice(200);
        product.setDescription("Đẹp quá trời đẹp lun");
        product.setPicture("imagesadasdas1");
        productRepository.save(product);
        productService.removeProductById(1L);

    }
    @Test
    public void updateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Áo Kimosa");
        product.setPrice(200);
        product.setDescription("Đẹp");
        product.setPicture("image1ádfsdafdsfdsfd");
        productRepository.save(product);
    }
}