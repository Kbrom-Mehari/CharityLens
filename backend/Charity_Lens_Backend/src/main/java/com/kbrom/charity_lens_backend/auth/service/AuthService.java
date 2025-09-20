package com.kbrom.charity_lens_backend.auth.service;

import com.kbrom.charity_lens_backend.auth.dto.*;
import com.kbrom.charity_lens_backend.auth.security.CustomUserDetails;
import com.kbrom.charity_lens_backend.charityOrganization.model.CharityOrganization;
import com.kbrom.charity_lens_backend.charityOrganization.repository.CharityOrganizationRepository;
import com.kbrom.charity_lens_backend.common.exception.DuplicateEntryException;
import com.kbrom.charity_lens_backend.donorProfile.DonorProfile;
import com.kbrom.charity_lens_backend.donorProfile.DonorProfileRepository;
import com.kbrom.charity_lens_backend.user.enums.Role;
import com.kbrom.charity_lens_backend.user.model.User;
import com.kbrom.charity_lens_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService  jwtService;
    private final CharityOrganizationRepository charityOrganizationRepository;
    private final DonorProfileRepository donorProfileRepository;
    private final AuthenticationManager authenticationManager;
    private final EmailVerificationService emailVerificationService;

    @Transactional
    public void registerDonor(RegisterDonorDTO registerDonorDTO) {
       checkEmailExists(registerDonorDTO.getEmail());
       checkUsernameExists(registerDonorDTO.getUsername());
      User user=new User();
      DonorProfile donorProfile=new DonorProfile();
      user.setEmail(registerDonorDTO.getEmail());
      user.setPassword(passwordEncoder.encode(registerDonorDTO.getPassword()));
      user.setUsername(registerDonorDTO.getUsername());
      user.getRoles().add(Role.DONOR);

      userRepository.save(user);
      donorProfile.setUser(user);
      donorProfileRepository.save(donorProfile);

      emailVerificationService.sendVerificationEmail(user);
    }
    @Transactional
    public void registerCharityOrganization(RegisterCharityOrganizationDTO registerCharityOrganizationDTO){
        checkEmailExists(registerCharityOrganizationDTO.getEmail());
        checkUsernameExists(registerCharityOrganizationDTO.getUsername());
       CharityOrganization charityOrganization=new CharityOrganization();
       User user=new User();
       user.setUsername(registerCharityOrganizationDTO.getUsername());
       user.setEmail(registerCharityOrganizationDTO.getEmail());
       user.setPassword(passwordEncoder.encode(registerCharityOrganizationDTO.getPassword()));
       user.getRoles().add(Role.CHARITY_ORG);


       userRepository.save(user);

       charityOrganization.setOrganizationName(registerCharityOrganizationDTO.getOrganizationName());
       charityOrganization.setUser(user);
       charityOrganizationRepository.save(charityOrganization);

       emailVerificationService.sendVerificationEmail(user);

   }
   public AuthResponseDTO login(LoginRequestDTO loginRequestDTO){
       UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
               loginRequestDTO.getLogin(),loginRequestDTO.getPassword()
       );
       Authentication authentication = authenticationManager.authenticate(authToken);
       CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
       UserSecurityDTO user=userDetails.getDomainUser();
       String token=jwtService.generateToken(userDetails);

       return new AuthResponseDTO(token,jwtService.getExpirationFromToken(token),user.username(),user.roles().stream().map(Role::name).toList());
   }


   private void checkEmailExists(String email) {
       if(userRepository.existsByEmail(email)){
           throw new DuplicateEntryException("Email already in use");
       }
   }
   private void checkUsernameExists(String username) {
       if(userRepository.existsByUsername(username)){
           throw new DuplicateEntryException("Username already taken");
       }
   }


}
