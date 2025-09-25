package com.kbrom.charity_lens_backend.user.service;

import com.kbrom.charity_lens_backend.auth.dto.ChangePasswordDTO;
import com.kbrom.charity_lens_backend.donorProfile.DonorProfile;
import com.kbrom.charity_lens_backend.donorProfile.DonorProfileRepository;
import com.kbrom.charity_lens_backend.user.dto.GetDonorProfileDTO;
import com.kbrom.charity_lens_backend.user.dto.UpdateDonorProfileDTO;
import com.kbrom.charity_lens_backend.common.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.common.util.mappers.GetUserMapper;
import com.kbrom.charity_lens_backend.user.enums.Role;
import com.kbrom.charity_lens_backend.user.model.User;
import com.kbrom.charity_lens_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
      private final UserRepository userRepository;
      private final DonorProfileRepository donorRepository;
      private final GetUserMapper getUserMapper;
      private final PasswordEncoder passwordEncoder;

      public User getUserById(Long id) {
          return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
      }
      public User getUserByEmail(String email) {
          return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
      }
      public User getUserByUsername(String username) {
          return userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
      }
      public List<User> getAllUsers() {
          return userRepository.findAll();
      }
      public void disableUserById(Long id) {
          User user = getUserById(id);
          if(user.isEnabled()) {
              user.setEnabled(false);
          }
      }
      public void enableUserById(Long id) {
          User user = getUserById(id);
          if(!user.isEnabled()) {
              user.setEnabled(true);
          }
      }
      public void flagUserById(Long id) {
          User user = getUserById(id);
          if(!user.isFlagged()) {
              user.setFlagged(true);
          }
      }
      public void unFlagUserById(Long id) {
          User user = getUserById(id);
          if(user.isFlagged()) {
              user.setFlagged(false);
          }
      }

      public GetDonorProfileDTO updateDonorProfile(UpdateDonorProfileDTO updateDTO, Long id) {
          User user=getUserById(id);
          DonorProfile profile=user.getProfile();
          if(updateDTO.getFirstName()!=null) {
              profile.setFirstName(updateDTO.getFirstName());
          }
          if(updateDTO.getLastName()!=null) {
              profile.setLastName(updateDTO.getLastName());
          }
          if(updateDTO.getPhoneNumber()!=null) {
              profile.setPhoneNumber(updateDTO.getPhoneNumber()); // needs verification in production
          }
          donorRepository.save(profile);

          return getUserMapper.toDTO(profile);
      }
      public void changePassword(Long id, ChangePasswordDTO changePasswordDTO, Principal principal) {
          User user=getUserById(id);
          boolean isAdmin=isAdmin(principal.getName());
          String currentUsername=principal.getName();

          if(!isAdmin&&!currentUsername.equals(user.getUsername())) {
              throw new AccessDeniedException("You don't have permission to change other's password");
          }
         if(!isAdmin&&!passwordConfirmed(changePasswordDTO,user)){
             throw new IllegalArgumentException("Incorrect password or new passwords does not match");
         }
         user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
         userRepository.save(user);

      }
      private boolean passwordConfirmed (ChangePasswordDTO changePasswordDTO,User user) {
          return changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())
                  && passwordEncoder.matches(user.getPassword(),changePasswordDTO.getOldPassword());
      }
      private boolean isAdmin (String username) {
          User user=getUserByUsername(username);
          return user.getRoles().contains(Role.SYS_ADMIN);

      }



}
