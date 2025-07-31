package com.kbrom.charity_lens_backend.mapper;

import com.kbrom.charity_lens_backend.dto.GetOrganizationDTO;
import com.kbrom.charity_lens_backend.model.CharityOrganization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetOrganizationMapper {
    GetOrganizationDTO toDTO (CharityOrganization organization);
    List<GetOrganizationDTO> toDTO_List (List<CharityOrganization> organizations);
}
