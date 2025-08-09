package com.kbrom.charity_lens_backend.service;

import com.kbrom.charity_lens_backend.dto.GetUserDTO;
import com.kbrom.charity_lens_backend.dto.RegisterUserDTO;
import com.kbrom.charity_lens_backend.dto.UpdateUserDTO;
import com.kbrom.charity_lens_backend.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.mapper.GetUserMapper;
import com.kbrom.charity_lens_backend.mapper.RegisterUserMapper;
import com.kbrom.charity_lens_backend.model.User;
import com.kbrom.charity_lens_backend.repository.UserRepository;
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
      public GetUserDTO createUser(RegisterUserDTO registerUserDTO){
          User user = registerUserMapper.toUser(registerUserDTO);
          User createdUser= userRepository.save(user);
          return getUserMapper.toDTO(createdUser);
      }
      public GetUserDTO updateUser(UpdateUserDTO updateUserDTO, Long id) {
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
