package com.kbrom.charity_lens_backend.auth.service;

import com.kbrom.charity_lens_backend.auth.dto.AuthResponse;
import com.kbrom.charity_lens_backend.auth.dto.LoginRequestDTO;
import com.kbrom.charity_lens_backend.auth.dto.RegisterCharityOrganizationDTO;
import com.kbrom.charity_lens_backend.auth.dto.RegisterDonorDTO;
import com.kbrom.charity_lens_backend.auth.exception.InvalidCredentialsException;
import com.kbrom.charity_lens_backend.charityOrganization.model.CharityOrganization;
import com.kbrom.charity_lens_backend.charityOrganization.repository.CharityOrganizationRepository;
import com.kbrom.charity_lens_backend.common.exception.DuplicateEntryException;
import com.kbrom.charity_lens_backend.common.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.user.enums.Role;
import com.kbrom.charity_lens_backend.user.model.User;
import com.kbrom.charity_lens_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService  jwtService;
    private final CharityOrganizationRepository charityOrganizationRepository;
    @Transactional
    public AuthResponse registerUser(RegisterDonorDTO registerDonorDTO) {
       checkEmailExists(registerDonorDTO.getEmail());
       checkUsernameExists(registerDonorDTO.getUsername());
      User user=new User();
      user.setEmail(registerDonorDTO.getEmail());
      user.setPassword(passwordEncoder.encode(registerDonorDTO.getPassword()));
      user.setFirstName(registerDonorDTO.getFirstName());
      user.setLastName(registerDonorDTO.getLastName());
      user.setUsername(registerDonorDTO.getUsername());
      user.setGender(registerDonorDTO.getGender());
      user.setRole(Role.DONOR);
      userRepository.save(user);

      String token=jwtService.generateToken(Map.of("role","DONOR"),user.getUsername());
      return new AuthResponse(token,user.getUsername(),user.getRole());
    }
    @Transactional
    public AuthResponse registerOrganization(RegisterCharityOrganizationDTO registerCharityOrganizationDTO){
        checkEmailExists(registerCharityOrganizationDTO.getEmail());
        checkUsernameExists(registerCharityOrganizationDTO.getUsername());
       CharityOrganization charityOrganization=new CharityOrganization();
       User user=new User();
       user.setUsername(registerCharityOrganizationDTO.getUsername());
       user.setEmail(registerCharityOrganizationDTO.getEmail());
       user.setPassword(passwordEncoder.encode(registerCharityOrganizationDTO.getPassword()));
       user.setRole(Role.CHARITY_ORG);
       userRepository.save(user);

       charityOrganization.setOrganizationName(registerCharityOrganizationDTO.getOrganizationName());
       charityOrganization.setUser(user);
       charityOrganizationRepository.save(charityOrganization);


       String token =jwtService.generateToken(Map.of("role","CHARITY_ORG"),user.getUsername());
       return new AuthResponse(token,user.getUsername(),user.getRole());
   }
   public AuthResponse login(LoginRequestDTO loginRequestDTO){
        User user;
        if(loginRequestDTO.getLogin().contains("@")){
            user=userRepository.findByEmail(loginRequestDTO.getLogin()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        }
        else {
            user = userRepository.findByUsername(loginRequestDTO.getLogin()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }
       if(passwordEncoder.matches(loginRequestDTO.getPassword(),user.getPassword())){
           String token=jwtService.generateToken(Map.of("role",user.getRole().name()),user.getUsername());
           return new AuthResponse(token,user.getUsername(),user.getRole());
       }
       else{
           throw new InvalidCredentialsException("Incorrect password");
       }


   }
   private void checkEmailExists(String email) {
       if(userRepository.existsByEmail(email)){
           throw new DuplicateEntryException("Email already in use");
       }
   }
   private void checkUsernameExists(String username) {
       if(userRepository.existsByUsername(username)){
           throw new DuplicateEntryException("Username already in use");
       }
   }

}
