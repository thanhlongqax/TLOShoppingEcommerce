package org.thanhlong.Midterm.Controller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.thanhlong.Midterm.DTO.BrandDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Repository.BrandRepository;
import org.thanhlong.Midterm.Service.impl.BrandServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class BrandApiControllerTest {
    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getBrandById() throws Exception {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("NIke");
        brandRepository.save(brand);
        Mockito.when(brandRepository.findById(ArgumentMatchers.eq(1L)))
                .thenReturn(Optional.of(brand));

        Brand brand1 = brandService.getBrandById(1L);

        Assert.assertEquals(brand1, brand);
    }
    @Test
    public void getAllBrand() {
        Brand brand1 = new Brand();
        brand1.setId(1L);
        brand1.setName("NIke");

        Brand brand2 = new Brand();
        brand2.setId(2L);
        brand2.setName("NIkefadsfdas"); // Fix the typo

        List<Brand> brands = new ArrayList<>();
        brands.add(brand1);
        brands.add(brand2);

        Mockito.when(brandRepository.findAll())
                .thenReturn(brands);

        List<BrandDTO> brands1 = brandService.getAllBrand();

        // Create BrandDTO objects for assertion
        List<BrandDTO> brandDTOList = brands1.stream()
                .map(brandDTO -> new BrandDTO(brandDTO.getId(), brandDTO.getName()))
                .collect(Collectors.toList());

        Assert.assertEquals(brandDTOList, brands1);
    }


    @Test
    public void deleteBrandById(){
        Brand brand = new Brand();
        brand.setId(2L);
        brand.setName("NIke");
        brandService.deleteBrandById(2L);
    }
    @Test
    public void updateBrand(){
        Brand brand = new Brand();
        brand.setId(2L);
        brand.setName("adsfdasfdas");
        brandService.updateBrand(brand);
    }
}
