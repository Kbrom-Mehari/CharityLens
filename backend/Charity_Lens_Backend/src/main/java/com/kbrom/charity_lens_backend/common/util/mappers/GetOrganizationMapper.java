package com.kbrom.charity_lens_backend.common.util.mappers;

import com.kbrom.charity_lens_backend.charityOrganization.dto.GetOrganizationDTO;
import com.kbrom.charity_lens_backend.charityOrganization.model.CharityOrganization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetOrganizationMapper {
    GetOrganizationDTO toDTO (CharityOrganization organization);
    List<GetOrganizationDTO> toDTO_List (List<CharityOrganization> organizations);
}
