package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Service.UserService;

import java.util.Optional;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = {"/Login" ,"/"})
    public String Login(Model model) {
        model.addAttribute("user",new User());
        return "SignIn_SignUp";
    }
    @PostMapping("/Login")
    public String loginForm(@ModelAttribute("user") User user, Model model)  {
        String email = user.getEmail();
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (existingUser.getPassword().equals(user.getPassword())) {
                return "redirect:/HomePage";
            } else {
                // Password does not match
                model.addAttribute("error", "Invalid email or password");
            }
        } else {
            // User with the given email does not exist
            model.addAttribute("error", "Invalid email or password");
        }
        return "redirect:/Login";
    }
    @PostMapping("/Register")
    public String registrationForm(@ModelAttribute("newUser") User newUser, Model model) {
        userService.saveUser(newUser);
        return "redirect:/Login";
    }
}
