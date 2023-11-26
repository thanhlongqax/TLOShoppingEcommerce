package org.thanhlong.Midterm.Api;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thanhlong.Midterm.Service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ProductServiceImpl productService;

    @Autowired
    public ImageController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{picture}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody Resource getImage(@PathVariable String picture) {
        return new ClassPathResource("static/image/" +  picture );
    }
}


