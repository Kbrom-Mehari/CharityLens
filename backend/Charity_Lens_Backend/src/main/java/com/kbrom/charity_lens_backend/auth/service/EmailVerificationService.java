package com.kbrom.charity_lens_backend.auth.service;

import com.kbrom.charity_lens_backend.common.dto.EmailRequest;
import com.kbrom.charity_lens_backend.common.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.common.service.EmailService;
import com.kbrom.charity_lens_backend.user.model.User;
import com.kbrom.charity_lens_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final UserRepository userRepository;
    private final VerificationJwtService verificationJwtService;
    private final EmailService emailService;

    public String createVerificationToken(String email) {
        return verificationJwtService.generateToken(email);
    }
    public void sendVerificationEmail(User user) {
        String token=createVerificationToken(user.getEmail());
        EmailRequest request=EmailRequest.builder()
                .to(List.of(user.getEmail()))
                .subject("Verify your email to login")
                .body("click this link to verify your email: "+" http://localhost:8080/api/auth/verify-email?token="+token)
                .html(false)
                .build();
        emailService.sendEmail(request);
    }

    @Transactional
    public boolean verifyEmail(String token) {
        if(!verificationJwtService.isTokenValid(token)){
            return false;
        }
        String email=verificationJwtService.extractEmail(token);
        User user= userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }
}
