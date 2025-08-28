package com.kbrom.charity_lens_backend.common.util.mappers;

import com.kbrom.charity_lens_backend.user.dto.RegisterUserDTO;
import com.kbrom.charity_lens_backend.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {
    RegisterUserDTO toDTO(User user);
    User toUser(RegisterUserDTO registerUserDTO);
}
