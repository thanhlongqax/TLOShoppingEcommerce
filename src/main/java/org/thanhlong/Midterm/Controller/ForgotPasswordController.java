package org.thanhlong.Midterm.Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thanhlong.Midterm.Models.EmailRequest;
import org.thanhlong.Midterm.Models.PasswordGenerator;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Service.EmailService;
import org.thanhlong.Midterm.Service.impl.UserServiceImpl;
import org.thymeleaf.context.Context;

@Controller
@Slf4j
public class ForgotPasswordController {
    private final UserServiceImpl userService;
    @Autowired
    EmailService emailService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    public ForgotPasswordController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model) {
        User newUser = new User();
        model.addAttribute("user",newUser);
        return "ForgetPassword";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(@ModelAttribute("user") User newuser  ,Model model) {
        try {
            User user = userService.findByEmail(newuser.getEmail());

            if (user == null) {
                model.addAttribute("error", "Không tìm được email cần tìm");
                return "ForgetPassword";
            } else {
                EmailRequest emailRequest = new EmailRequest();
                emailRequest.setTo(user.getEmail());
                String generatedPassword = PasswordGenerator.generateRandomPassword(12, true, true, true);
                user.setPassword(passwordEncoder.encode(generatedPassword));
                userService.saveUser(user);
                emailRequest.setBody(generatedPassword);
                emailRequest.setSubject("Mật Khẩu Của Bạn Là ");
                Context context = new Context();
                context.setVariable("message", emailRequest.getBody());

                emailService.sendEmailWithHtmlTemplate(emailRequest.getTo(), emailRequest.getSubject(), "email-template", context);
                return "redirect:/Login";
            }
        } catch (Exception e) {
            // Handle the exception, you can log it or return an error message
            return "An error occurred: " + e.getMessage();
        }
    }
}
