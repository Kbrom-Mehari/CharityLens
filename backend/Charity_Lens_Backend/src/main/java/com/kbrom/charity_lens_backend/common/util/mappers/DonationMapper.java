package com.kbrom.charity_lens_backend.common.util.mappers;

import com.kbrom.charity_lens_backend.donation.dto.DonationDTO;
import com.kbrom.charity_lens_backend.donation.model.Donation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonationMapper {
    @Mapping(source="donor.firstName",target="donorFirstName")
    @Mapping(source="donor.lastName",target = "donorLastName")
    @Mapping(source="campaign.id",target="campaignId")
    @Mapping(source="project.id",target="projectId")
    DonationDTO toDTO(Donation donation);
    List<DonationDTO> toDTO_List(List<Donation> donations);
}
