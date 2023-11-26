package org.thanhlong.Midterm.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.DTO.ColorDTO;
import org.thanhlong.Midterm.Models.Color;
import org.thanhlong.Midterm.Repository.ColorRepository;
import org.thanhlong.Midterm.Service.ColorService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepository colorRepository;
    @Override
    public List<ColorDTO> getAllColor() {
        List<Color> colors = colorRepository.findAll();
        List<ColorDTO> colorDTOS = new ArrayList<>();
        colors.forEach(color -> {
            ColorDTO colorDTO = new ColorDTO();
            colorDTO.setId(color.getId());
            colorDTO.setName(color.getName());
            colorDTOS.add(colorDTO);
        });
        return colorDTOS;
    }
    @Override
    public Color getColorById(Long id){
        return colorRepository.findById(id).orElse(null);
    }
    @Override
    public void addColor(Color color){
        colorRepository.save(color);
    }
    @Override
    public void updateColor(Color color){
        colorRepository.save(color);
    }
    @Override
    public void deleteColorById(Long id){
        colorRepository.deleteById(id);
    }

}
