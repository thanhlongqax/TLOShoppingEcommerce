package org.thanhlong.Midterm.Service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.Service.EmailService;
import java.io.File;
import org.springframework.core.io.FileSystemResource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService { ;
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    TemplateEngine templateEngine;
//    @Override
//    public String sendSimpleMail(EmailDetails details)
//    {
//
//        try {
//
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            // Setting up necessary details
//            mailMessage.setFrom("thanhlongndp@gmail.com");
//            mailMessage.setTo(details.getRecipient());
//            mailMessage.setText(details.getMsgBody());
//            mailMessage.setSubject(details.getSubject());
//
//            emailSender.send(mailMessage);
//            return "Mail Sent Successfully";
//        }
//        catch (Exception e) {
//            return "Error while Sending Mail";
//        }
//    }
//    @Override
//    public String sendMailWithAttachment(EmailDetails details)
//    {
//        // Creating a mime message
//        MimeMessage mimeMessage = emailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom("thanhlongndp@gmail.com");
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(details.getSubject());
//
//            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
//            mimeMessageHelper.addAttachment(file.getFilename(), file);
//            // Sending the mail
//            mimeMessage = mimeMessageHelper.getMimeMessage();
//            emailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        }
//        catch (MessagingException e) {
//            e.printStackTrace();  // Print the exception details
//            return "Error while sending mail: " + e.getMessage();
//        }
//    }
    @Override
    public void sendEmailWithHtmlTemplate(String to, String subject, String templateName, Context context) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setFrom("thanhlongndp@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle exception
        }
    }

}