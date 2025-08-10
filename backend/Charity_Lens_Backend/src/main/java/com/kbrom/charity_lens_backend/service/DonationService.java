package com.kbrom.charity_lens_backend.service;

import com.kbrom.charity_lens_backend.dto.DonateToCampaignDTO;
import com.kbrom.charity_lens_backend.dto.DonateToProjectDTO;
import com.kbrom.charity_lens_backend.dto.DonationDTO;
import com.kbrom.charity_lens_backend.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.mapper.DonationMapper;
import com.kbrom.charity_lens_backend.model.Campaign;
import com.kbrom.charity_lens_backend.model.Donation;
import com.kbrom.charity_lens_backend.model.Project;
import com.kbrom.charity_lens_backend.model.User;
import com.kbrom.charity_lens_backend.model.enums.Currency;
import com.kbrom.charity_lens_backend.repository.CampaignRepository;
import com.kbrom.charity_lens_backend.repository.DonationRepository;
import com.kbrom.charity_lens_backend.repository.ProjectRepository;
import com.kbrom.charity_lens_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final DonationMapper donationMapper;

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
    }
    private Donation createDonation(User user, BigDecimal amount, Currency currency) {
        Donation donation=new Donation();
        donation.setDonor(user);
        donation.setDonationAmount(amount);
        donation.setCurrency(currency);
        donation.setCreatedAt(LocalDateTime.now());
        return donation;
    }

    public DonationDTO donateToProject(DonateToProjectDTO donateToProjectDTO) {
        BigDecimal newDonation=donateToProjectDTO.getAmount();
        if(newDonation==null){newDonation=BigDecimal.ZERO;} //preventing null pointer exception

        User user = findUser(donateToProjectDTO.getUserId());
        Project project=projectRepository.findById(donateToProjectDTO.getProjectId()).orElseThrow(()->new ResourceNotFoundException("Project not found"));
        Donation donation=createDonation(user, newDonation,donateToProjectDTO.getCurrency());
        donation.setProject(project);
        donationRepository.save(donation);
        project.setTotalDonation(project.getTotalDonation().add(newDonation));
        projectRepository.save(project);
        return donationMapper.toDTO(donation);
    }
    public DonationDTO donateToCampaign(DonateToCampaignDTO donateToCampaignDTO) {
        BigDecimal newDonation= donateToCampaignDTO.getAmount();
        if(newDonation==null) {newDonation=BigDecimal.ZERO;} // defensive approach to prevent null pointer exception

        User user = findUser(donateToCampaignDTO.getUserId());
        Campaign campaign=campaignRepository.findById(donateToCampaignDTO.getCampaignId()).orElseThrow(()->new ResourceNotFoundException("Campaign not found"));
        Donation donation=createDonation(user, newDonation,donateToCampaignDTO.getCurrency());
        donation.setCampaign(campaign);
        donationRepository.save(donation);
        campaign.setTotalDonation(campaign.getTotalDonation().add(newDonation));
        campaignRepository.save(campaign);
        return donationMapper.toDTO(donation);
    }
    public List<DonationDTO> findDonationsByCampaignId(Long campaignId) {
        List<Donation> donations=donationRepository.findDonationsByCampaignId(campaignId);
        return donationMapper.toDTO_List(donations);
    }
    public List<DonationDTO> findDonationsByProjectId(Long projectId) {
        List<Donation> donations=donationRepository.findDonationsByProjectId(projectId);
        return donationMapper.toDTO_List(donations);
    }
    public List<DonationDTO> findDonationsByDonorId(Long donorId) {
        List<Donation> donations =donationRepository.findDonationsByDonorId(donorId);
        return donationMapper.toDTO_List(donations);
    }
    public List<DonationDTO> findDonationsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Donation> donations=donationRepository.findDonationsByCreatedAtBetween(startDate, endDate);
        return donationMapper.toDTO_List(donations);
    }

}
