package org.thanhlong.Midterm.Service;


import org.thymeleaf.context.Context;

public interface EmailService {
    void sendEmailWithHtmlTemplate(String to, String subject, String templateName, Context context);
}
