package com.kbrom.charity_lens_backend.common.service;

import com.kbrom.charity_lens_backend.common.dto.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
   private final JavaMailSender mailSender;
   @Override
    public void sendEmail(EmailRequest emailRequest) {
       try{
           MimeMessage message=mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message,true);
           helper.setTo(emailRequest.getTo().toArray(new String[0]));
           helper.setSubject(emailRequest.getSubject());
           helper.setText(emailRequest.getBody(),emailRequest.isHtml());
           mailSender.send(message);
       }
       catch(MessagingException e) {
           throw new RuntimeException("Email sending failed",e);
       }
   }
}