package org.thanhlong.Midterm.Service;

import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.DTO.ColorDTO;
import org.thanhlong.Midterm.Models.Brand;
import org.thanhlong.Midterm.Models.Color;

import java.util.List;
@Service
public interface ColorService {
    List<ColorDTO> getAllColor();
    Color getColorById(Long id);
    void addColor(Color color);
    void updateColor(Color color);
    void deleteColorById(Long id);
}
