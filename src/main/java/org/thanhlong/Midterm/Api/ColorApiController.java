package org.thanhlong.Midterm.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thanhlong.Midterm.DTO.ColorDTO;
import org.thanhlong.Midterm.Service.impl.ColorServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorApiController {

    private final ColorServiceImpl colorService;

    @Autowired
    public ColorApiController(ColorServiceImpl colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public List<ColorDTO> getAllColors() {
        List<ColorDTO> colors = colorService.getAllColor();
        return colors;
    }
}