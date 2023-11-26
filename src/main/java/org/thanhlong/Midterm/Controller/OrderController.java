package org.thanhlong.Midterm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping(value = {"/Admin/Order"})
    public String Order_Admin() {
        return "/Admin/Order_Admin";
    }
}
