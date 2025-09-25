package com.kbrom.charity_lens_backend.common.util.mappers;

import com.kbrom.charity_lens_backend.donorProfile.DonorProfile;
import com.kbrom.charity_lens_backend.user.dto.GetDonorProfileDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserMapper {
    GetDonorProfileDTO toDTO(DonorProfile donorProfile);
}
