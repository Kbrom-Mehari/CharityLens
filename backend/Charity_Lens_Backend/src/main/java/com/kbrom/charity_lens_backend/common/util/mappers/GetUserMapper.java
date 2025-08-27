package com.kbrom.charity_lens_backend.common.util.mappers;

import com.kbrom.charity_lens_backend.user.dto.GetUserDTO;
import com.kbrom.charity_lens_backend.user.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetUserMapper {
    GetUserDTO toDTO(User user);
    List<GetUserDTO> toDTO_List(List<User> users);
}
