package com.kbrom.charity_lens_backend.service;

import com.kbrom.charity_lens_backend.dto.GetOrganizationDTO;
import com.kbrom.charity_lens_backend.dto.RegisterOrganizationDTO;
import com.kbrom.charity_lens_backend.dto.UpdateOrganizationDTO;
import com.kbrom.charity_lens_backend.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.mapper.GetOrganizationMapper;
import com.kbrom.charity_lens_backend.mapper.RegisterOrganizationMapper;
import com.kbrom.charity_lens_backend.model.CharityOrganization;
import com.kbrom.charity_lens_backend.repository.CharityOrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharityOrganizationService {
    private final GetOrganizationMapper getOrganizationMapper;
    private final CharityOrganizationRepository charityOrganizationRepository;
    private final RegisterOrganizationMapper registerOrganizationMapper;

    public GetOrganizationDTO registerOrganization(RegisterOrganizationDTO registerOrganizationDTO) {
        CharityOrganization organization=registerOrganizationMapper.toOrganization(registerOrganizationDTO);
        charityOrganizationRepository.save(organization);
        return getOrganizationMapper.toDTO(organization);
    }
    public List<GetOrganizationDTO> findAllOrganizations() {
        List<CharityOrganization> organizations= charityOrganizationRepository.findAll();
        return getOrganizationMapper.toDTO_List(organizations);
    }
    public GetOrganizationDTO getOrganizationById(Long id) {
        CharityOrganization organization = charityOrganizationRepository.findById(id)
                         .orElseThrow(()->new ResourceNotFoundException("Organization not found"));
        return getOrganizationMapper.toDTO(organization);
    }
    public GetOrganizationDTO getOrganizationByEmail(String email) {
        CharityOrganization organization = charityOrganizationRepository.findByEmail(email);
        return getOrganizationMapper.toDTO(organization);
    }
    public List<GetOrganizationDTO> searchOrganizationsByName(String name){
        List<CharityOrganization> organizations= charityOrganizationRepository.searchCharityOrganizationByNameContainingIgnoreCase(name);
        return getOrganizationMapper.toDTO_List(organizations);
    }
    public void deleteById(Long id) {
        CharityOrganization organization=charityOrganizationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Organization not found"));
        charityOrganizationRepository.delete(organization);
    }
    public GetOrganizationDTO updateOrganization(Long id ,UpdateOrganizationDTO updateOrganizationDTO) {
        CharityOrganization updated=charityOrganizationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Organization not found"));
        if(updateOrganizationDTO.getName()!=null){
            updated.setName(updateOrganizationDTO.getName());
        }
        if(updateOrganizationDTO.getPhone()!=null){
            updated.setPhone(updateOrganizationDTO.getPhone());
        }
        if(updateOrganizationDTO.getPassword()!=null){
            updated.setPassword(updateOrganizationDTO.getPassword());
        }
        return getOrganizationMapper.toDTO(updated);
    }

}
