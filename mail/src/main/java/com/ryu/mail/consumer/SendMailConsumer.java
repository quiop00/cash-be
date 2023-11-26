package com.ryu.mail.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.ryu.common.models.RequestOtp;
import com.ryu.common.utils.JsonConverter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class SendMailConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;
    
    @KafkaListener(topics = "${spring.kafka.verify-email}", groupId = "${spring.kafka.mail-group-id}", containerFactory = "mailListenerContainerFactory")
    public void sendOtp(@Payload String content) {
        RequestOtp requestOtp = JsonConverter.deserializeObject(content, RequestOtp.class);
        Context context = new Context();
        context.setVariable("otp", requestOtp.getOtp());
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(requestOtp.getEmail());
            helper.setSubject("Verify account");
            String htmlContent = templateEngine.process("register_otp", context);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "${spring.kafka.verify-email-success}", groupId = "${spring.kafka.mail-group-id}", containerFactory = "mailListenerContainerFactory")
    public void verifySuccess(@Payload String email) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Verify account success");
            String htmlContent = templateEngine.process("register_otp", new Context());
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
