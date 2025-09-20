package com.kbrom.charity_lens_backend.auth.controller;

import com.kbrom.charity_lens_backend.auth.dto.AuthResponseDTO;
import com.kbrom.charity_lens_backend.auth.dto.LoginRequestDTO;
import com.kbrom.charity_lens_backend.auth.dto.RegisterCharityOrganizationDTO;
import com.kbrom.charity_lens_backend.auth.dto.RegisterDonorDTO;
import com.kbrom.charity_lens_backend.auth.exception.EmailVerificationException;
import com.kbrom.charity_lens_backend.auth.service.AuthService;
import com.kbrom.charity_lens_backend.auth.service.EmailVerificationService;
import com.kbrom.charity_lens_backend.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/signup/donor")
    public ResponseEntity<ApiResponse> signupDonor (@Valid @RequestBody RegisterDonorDTO registerDonorDTO){
        authService.registerDonor(registerDonorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Donor registered successfully! please check your email to verify your account",true));
    }
    @PostMapping("/signup/organization")
    public  ResponseEntity<ApiResponse> signupOrganization(@Valid @RequestBody RegisterCharityOrganizationDTO registerCharityOrganizationDTO){
        authService.registerCharityOrganization(registerCharityOrganizationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Organization registered successfully! please check your email to verify your account",true));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
    @GetMapping("/verify-email")
    public ResponseEntity<ApiResponse> verifyEmail(@RequestParam("token")  String token){
        if(emailVerificationService.verifyEmail(token)){
            return ResponseEntity.ok(new ApiResponse("Email verified! You can now login!",true));
        }
        else
            throw new EmailVerificationException("Invalid or Expired token!");

    }
}
