package com.kbrom.charity_lens_backend.service;

import com.kbrom.charity_lens_backend.dto.GetUserDTO;
import com.kbrom.charity_lens_backend.mapper.GetUserMapper;
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
      public GetUserDTO getUserByEmail(String email) {
        User user= userRepository.findByEmail(email);
        return getUserMapper.toDTO(user);
      }
      public List<GetUserDTO> getAllUsers(){
          List<User> users= userRepository.findAll();
          return getUserMapper.toListDTO(users);
      }
      public GetUserDTO getUserById(Long id) {
          User user = userRepository.findById(id).get();
          return getUserMapper.toDTO(user);
      }

}
