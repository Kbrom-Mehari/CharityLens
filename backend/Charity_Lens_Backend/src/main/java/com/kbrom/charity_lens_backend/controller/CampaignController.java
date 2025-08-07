package com.kbrom.charity_lens_backend.controller;

import com.kbrom.charity_lens_backend.dto.CreateCampaignDTO;
import com.kbrom.charity_lens_backend.dto.GetCampaignDTO;
import com.kbrom.charity_lens_backend.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;

    @GetMapping()
    public ResponseEntity<List<GetCampaignDTO>> findAll() {
        return ResponseEntity.ok().body(campaignService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<GetCampaignDTO> createCampaign(@RequestBody CreateCampaignDTO createCampaignDTO) {
        return ResponseEntity.ok().body(campaignService.createCampaign(createCampaignDTO));
    }
}
