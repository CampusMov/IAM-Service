package com.campusmov.platform.iamservice.iam.infrastructure.services.implementation;

import com.campusmov.platform.iamservice.iam.infrastructure.services.EmailService;
import com.campusmov.platform.iamservice.iam.infrastructure.model.Email;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }
    @Override
    public void sendEmail(Email email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setTo(email.getAddressee());
            helper.setSubject(email.getSubject());

            Context context = new Context();
            context.setVariable("verificationCode", email.getVerificationCode());

            String contentHtml = templateEngine.process("email", context);

            helper.setText(contentHtml, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }

}
