package org.thanhlong.Midterm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thanhlong.Midterm.Models.EmailRequest;
import org.thanhlong.Midterm.Service.EmailService;
import org.thymeleaf.context.Context;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;
    @PostMapping("/send-html-email")
    public String sendHtmlEmail(@RequestBody EmailRequest emailRequest) {

        Context context = new Context();
        context.setVariable("message", emailRequest.getBody());

        emailService.sendEmailWithHtmlTemplate(emailRequest.getTo(), emailRequest.getSubject(), "email-template", context);
        return "HTML email sent successfully!";
    }
}
