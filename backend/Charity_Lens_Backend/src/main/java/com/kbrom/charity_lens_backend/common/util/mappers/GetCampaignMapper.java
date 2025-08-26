package com.kbrom.charity_lens_backend.common.util.mappers;

import com.kbrom.charity_lens_backend.campaign.dto.GetCampaignDTO;
import com.kbrom.charity_lens_backend.campaign.model.Campaign;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetCampaignMapper {
    GetCampaignDTO toDTO (Campaign campaign);
    List<GetCampaignDTO> toDTO_List (List<Campaign> campaigns);
}
