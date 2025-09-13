package com.kbrom.charity_lens_backend.auth.service;

import com.kbrom.charity_lens_backend.auth.dto.AuthResponseDTO;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService  jwtService;
    private final CharityOrganizationRepository charityOrganizationRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponseDTO registerUser(RegisterDonorDTO registerDonorDTO) {
       checkEmailExists(registerDonorDTO.getEmail());
       checkUsernameExists(registerDonorDTO.getUsername());
      User user=new User();
      user.setEmail(registerDonorDTO.getEmail());
      user.setPassword(passwordEncoder.encode(registerDonorDTO.getPassword()));
      user.setFirstName(registerDonorDTO.getFirstName());
      user.setLastName(registerDonorDTO.getLastName());
      user.setUsername(registerDonorDTO.getUsername());
      user.setGender(registerDonorDTO.getGender());
      user.setRoles(List.of(Role.DONOR));
      userRepository.save(user);

      String token=jwtService.generateToken(Map.of("roles",user.getRoles()),user.getUsername());
      return new AuthResponseDTO(token,jwtService.getExpirationFromToken(token),user.getUsername(),user.getRoles().stream().map(Role::name).toList());
    }
    @Transactional
    public AuthResponseDTO registerOrganization( RegisterCharityOrganizationDTO registerCharityOrganizationDTO){
        checkEmailExists(registerCharityOrganizationDTO.getEmail());
        checkUsernameExists(registerCharityOrganizationDTO.getUsername());
       CharityOrganization charityOrganization=new CharityOrganization();
       User user=new User();
       user.setUsername(registerCharityOrganizationDTO.getUsername());
       user.setEmail(registerCharityOrganizationDTO.getEmail());
       user.setPassword(passwordEncoder.encode(registerCharityOrganizationDTO.getPassword()));
       user.setRoles(List.of(Role.CHARITY_ORG));
       userRepository.save(user);

       charityOrganization.setOrganizationName(registerCharityOrganizationDTO.getOrganizationName());
       charityOrganization.setUser(user);
       charityOrganizationRepository.save(charityOrganization);


       String token =jwtService.generateToken(Map.of("roles",user.getRoles()),user.getUsername());
       return new AuthResponseDTO(token,jwtService.getExpirationFromToken(token),user.getUsername(),user.getRoles().stream().map(Role::name).toList());
   }
   public AuthResponseDTO login(@NotNull LoginRequestDTO loginRequestDTO){
       UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
               loginRequestDTO.getLogin(),loginRequestDTO.getPassword()
       );
       Authentication authentication = authenticationManager.authenticate(authToken);
       User user = (User) authentication.getPrincipal();
       String token=jwtService.generateToken(Map.of("roles",user.getRoles().stream().map(Role::name).toList()),user.getUsername());

       return new AuthResponseDTO(token,jwtService.getExpirationFromToken(token),user.getUsername(),user.getRoles().stream().map(Role::name).toList());
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
