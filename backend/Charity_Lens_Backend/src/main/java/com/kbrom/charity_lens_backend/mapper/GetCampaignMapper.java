package com.kbrom.charity_lens_backend.mapper;

import com.kbrom.charity_lens_backend.dto.GetCampaignDTO;
import com.kbrom.charity_lens_backend.model.Campaign;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetCampaignMapper {
    GetCampaignDTO toDTO (Campaign campaign);
    List<GetCampaignDTO> toDTO_List (List<Campaign> campaigns);
}
