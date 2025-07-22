package com.kbrom.charity_lens_backend.mapper;

import com.kbrom.charity_lens_backend.dto.RegisterUserDTO;
import com.kbrom.charity_lens_backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {
    RegisterUserDTO toDTO(User user);
    User toUser(RegisterUserDTO registerUserDTO);
}
