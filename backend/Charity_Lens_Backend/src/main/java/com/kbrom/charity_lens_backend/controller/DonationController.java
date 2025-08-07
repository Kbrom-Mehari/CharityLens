package com.kbrom.charity_lens_backend.controller;

import com.kbrom.charity_lens_backend.dto.DonateToCampaignDTO;
import com.kbrom.charity_lens_backend.dto.DonateToProjectDTO;
import com.kbrom.charity_lens_backend.dto.DonationDTO;
import com.kbrom.charity_lens_backend.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donations")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/donate-to-project")
    public ResponseEntity<DonationDTO> donateToProject(@RequestBody DonateToProjectDTO donateToProjectDTO) {
        return ResponseEntity.ok().body(donationService.donateToProject(donateToProjectDTO));
    }
    @PostMapping("/donate-to-campaign")
    public ResponseEntity<DonationDTO> donateToCampaign(@RequestBody DonateToCampaignDTO donateToCampaignDTO) {
        return ResponseEntity.ok().body(donationService.donateToCampaign(donateToCampaignDTO));
    }

}
