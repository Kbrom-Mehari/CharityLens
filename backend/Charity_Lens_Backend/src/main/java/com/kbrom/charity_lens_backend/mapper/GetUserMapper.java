package com.kbrom.charity_lens_backend.mapper;

import com.kbrom.charity_lens_backend.dto.GetUserDTO;
import com.kbrom.charity_lens_backend.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetUserMapper {
    GetUserDTO toDTO(User user);
    User toUser(GetUserDTO getUserDTO);
    List<GetUserDTO> toListDTO(List<User> users);
    List<User> toUsers(List<GetUserDTO> getUserDTOs);
}
