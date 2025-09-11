package com.kbrom.charity_lens_backend.user.service;

import com.kbrom.charity_lens_backend.user.dto.GetUserDTO;
import com.kbrom.charity_lens_backend.user.dto.RegisterUserDTO;
import com.kbrom.charity_lens_backend.user.dto.UserUpdateDTO;
import com.kbrom.charity_lens_backend.common.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.common.util.mappers.GetUserMapper;
import com.kbrom.charity_lens_backend.common.util.mappers.RegisterUserMapper;
import com.kbrom.charity_lens_backend.user.model.User;
import com.kbrom.charity_lens_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
      private final UserRepository userRepository;
      private final GetUserMapper getUserMapper;
      private final RegisterUserMapper registerUserMapper;
      public GetUserDTO getUserByEmail(String email) {
        User user= userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        return getUserMapper.toDTO(user);
      }
      public List<GetUserDTO> getAllUsers(){
          List<User> users= userRepository.findAll();
          return getUserMapper.toDTO_List(users);
      }
      public GetUserDTO getUserById(Long id) {
          User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
          return getUserMapper.toDTO(user);
      }
      public void deleteUserById(Long id) {
          User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
          userRepository.delete(user);
      }

      public GetUserDTO updateUser(UserUpdateDTO updateUserDTO, Long id) {
          User oldUser=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
          if(updateUserDTO.getFirstName()!=null) {
              oldUser.setFirstName(updateUserDTO.getFirstName());
          }
          if(updateUserDTO.getLastName()!=null) {
              oldUser.setLastName(updateUserDTO.getLastName());
          }
          if(updateUserDTO.getUsername()!=null) {
              oldUser.setUsername(updateUserDTO.getUsername());
          }
          if(updateUserDTO.getPassword()!=null) {
              oldUser.setPassword(updateUserDTO.getPassword());
          }
          return getUserMapper.toDTO(oldUser);
      }

}
