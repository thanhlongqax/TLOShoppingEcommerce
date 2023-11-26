package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thanhlong.Midterm.DTO.ColorDTO;
import org.thanhlong.Midterm.Models.Category;
import org.thanhlong.Midterm.Models.Color;
import org.thanhlong.Midterm.Service.impl.ColorServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
public class ColorController {
    private final ColorServiceImpl colorService;

    @Autowired
    public ColorController(ColorServiceImpl colorService){this.colorService = colorService;}
    @GetMapping(value = {"/Admin/Color"})
    public String Color_Admin(Model model) {
        List<ColorDTO> color = colorService.getAllColor();
        model.addAttribute("colors" , color);
        return "/Admin/Color_Admin";
    }
    @GetMapping(value = {"/Admin/Color/Add"})
    public String addColor_Admin(Model model) {
        model.addAttribute("color", new Color());
        return "/Admin/AddColor_Admin"; // Đặt tên template hiển thị form thêm thương hiệu
    }

    @PostMapping(value = {"/Admin/Color/Add"})
    public String addColor_Admin(@ModelAttribute("color") Color color) {
        colorService.addColor(color);
        return "redirect:/Admin/Color";
    }
    @GetMapping("/editColor/{id}")
    public ModelAndView editColor_Admin(@PathVariable("id") Long id, Model model) {
        ModelAndView colorView = new ModelAndView("/Admin/EditColor_Admin");
        Color color = colorService.getColorById(id);
        model.addAttribute("color", color);
        return colorView;
    }
    @PostMapping("/editColor/{id}")
    public String editColor_Admin(@PathVariable Long id, @ModelAttribute("color") Color updateColor) {
        colorService.updateColor(updateColor);
        return "redirect:/Admin/Color";
    }

    @GetMapping("/deleteColor/{id}")
    public String deleteColor_Admin(@PathVariable Long id) {
        colorService.deleteColorById(id);
        return "redirect:/Admin/Color";
    }

}
