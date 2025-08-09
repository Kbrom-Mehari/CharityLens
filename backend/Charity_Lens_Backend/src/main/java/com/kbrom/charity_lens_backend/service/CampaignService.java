package com.kbrom.charity_lens_backend.service;

import com.kbrom.charity_lens_backend.dto.CreateCampaignDTO;
import com.kbrom.charity_lens_backend.dto.GetCampaignDTO;
import com.kbrom.charity_lens_backend.mapper.GetCampaignMapper;
import com.kbrom.charity_lens_backend.model.Campaign;
import com.kbrom.charity_lens_backend.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final GetCampaignMapper getCampaignMapper;

    public GetCampaignDTO createCampaign(CreateCampaignDTO createCampaignDTO) {
        Campaign campaign=new Campaign();
        campaign.setTitle(createCampaignDTO.getTitle());
        campaign.setDescription(createCampaignDTO.getDescription());
        campaign.setObjective(createCampaignDTO.getObjective());
        campaign.setDonationGoal(createCampaignDTO.getDonationGoal());
        campaign.setEndDate(createCampaignDTO.getEndDate());
        campaign.setCause(createCampaignDTO.getCause());
        campaignRepository.save(campaign);
        return getCampaignMapper.toDTO(campaign);
    }
    public List<GetCampaignDTO> findAll() {
        List<Campaign> campaigns= campaignRepository.findAll();
        return getCampaignMapper.toDTO_List(campaigns);
    }
    public List<GetCampaignDTO> getFullyFundedCampaigns() {
       List<Campaign> campaigns=campaignRepository.findAllByDonationGoalAchieved();
       return getCampaignMapper.toDTO_List(campaigns);
    }

}
