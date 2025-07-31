package com.kbrom.charity_lens_backend.mapper;

import com.kbrom.charity_lens_backend.dto.RegisterOrganizationDTO;
import com.kbrom.charity_lens_backend.model.CharityOrganization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterOrganizationMapper {
    CharityOrganization toOrganization(RegisterOrganizationDTO registerOrganizationDTO);

}
