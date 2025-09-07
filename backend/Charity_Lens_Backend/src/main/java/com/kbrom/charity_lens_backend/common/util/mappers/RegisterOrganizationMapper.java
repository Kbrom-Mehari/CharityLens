package com.kbrom.charity_lens_backend.common.util.mappers;

import com.kbrom.charity_lens_backend.charityOrganization.dto.RegisterOrganizationDTO;
import com.kbrom.charity_lens_backend.charityOrganization.model.CharityOrganization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterOrganizationMapper {
    CharityOrganization toOrganization(RegisterOrganizationDTO registerOrganizationDTO);

}
