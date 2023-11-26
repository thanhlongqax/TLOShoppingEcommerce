package org.thanhlong.Midterm.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thanhlong.Midterm.Models.Role;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Service.impl.RoleServiceImpl;
import org.thanhlong.Midterm.Service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class AuthController {
    private final UserServiceImpl userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    RoleServiceImpl roleService;


    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping(value = {"/Login" ,"/"})
    public String loginForm(Model model , User user) {
        model.addAttribute("user",user);
        return "Login";
    }
    @GetMapping(value = {"/Register"})
    public String registerForm(Model model){
        model.addAttribute("newUser", new User());
        return "Register";
    }
    @PostMapping("/Register")
    public String registerForm(@ModelAttribute("newUser") User newUser, Model model) {
        String password = newUser.getPassword();
        newUser.setPassword(passwordEncoder.encode(password));
        //set mac dinh role user
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findRoleById(2).get());
        newUser.setRoles(roles);
        try {
            userService.saveUser(newUser);
            return "redirect:/Login";
        }catch(Exception e){
            model.addAttribute("errorRegister", "Đăng ký không thành công, email đã tồn tại");
            return "redirect:/Register";
        }
    }
}
