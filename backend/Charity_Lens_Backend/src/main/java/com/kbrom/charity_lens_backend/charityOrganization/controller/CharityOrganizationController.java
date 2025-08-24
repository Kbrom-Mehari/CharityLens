package com.kbrom.charity_lens_backend.charityOrganization.controller;

import com.kbrom.charity_lens_backend.charityOrganization.dto.GetOrganizationDTO;
import com.kbrom.charity_lens_backend.charityOrganization.dto.RegisterOrganizationDTO;
import com.kbrom.charity_lens_backend.charityOrganization.dto.UpdateOrganizationDTO;
import com.kbrom.charity_lens_backend.charityOrganization.service.CharityOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class CharityOrganizationController {
    private final CharityOrganizationService charityOrganizationService;

    @PostMapping
    public ResponseEntity<GetOrganizationDTO> registerUser(@RequestBody RegisterOrganizationDTO registerOrganizationDTO) {
       GetOrganizationDTO org= charityOrganizationService.registerOrganization(registerOrganizationDTO);
       return ResponseEntity.ok(org);
    }
    @GetMapping
    public ResponseEntity<List<GetOrganizationDTO>> findAll() {
        List<GetOrganizationDTO> organizations= charityOrganizationService.findAllOrganizations();
        return ResponseEntity.ok().body(organizations);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetOrganizationDTO> findById(@PathVariable Long id) {
        GetOrganizationDTO org = charityOrganizationService.getOrganizationById(id);
        return ResponseEntity.ok().body(org);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<GetOrganizationDTO> updateOrganization(@PathVariable Long id, @RequestBody UpdateOrganizationDTO updateOrganizationDTO) {
        GetOrganizationDTO org= charityOrganizationService.updateOrganization(id,updateOrganizationDTO);
        return ResponseEntity.ok(org);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        charityOrganizationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
