package com.kbrom.charity_lens_backend.user.charityOrganization;

import com.kbrom.charity_lens_backend.user.dto.GetOrganizationDTO;
import com.kbrom.charity_lens_backend.user.dto.UpdateOrganizationDTO;
import com.kbrom.charity_lens_backend.common.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.common.util.mappers.GetOrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharityOrganizationService {
    private final GetOrganizationMapper getOrganizationMapper;
    private final CharityOrganizationRepository charityOrganizationRepository;


    public List<GetOrganizationDTO> findAllOrganizations() {
        List<CharityOrganization> organizations= charityOrganizationRepository.findAll();
        return getOrganizationMapper.toDTO_List(organizations);
    }
    public GetOrganizationDTO getOrganizationById(Long id) {
        CharityOrganization organization = charityOrganizationRepository.findById(id)
                         .orElseThrow(()->new ResourceNotFoundException("Organization not found"));
        return getOrganizationMapper.toDTO(organization);
    }


    public void deleteById(Long id) {
        CharityOrganization organization=charityOrganizationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Organization not found"));
        charityOrganizationRepository.delete(organization);
    }
    public GetOrganizationDTO updateOrganization(Long id ,UpdateOrganizationDTO updateOrganizationDTO) {
        CharityOrganization updated=charityOrganizationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Organization not found"));
        if(updateOrganizationDTO.getName()!=null){
            updated.setOrganizationName(updateOrganizationDTO.getName());
        }

        return getOrganizationMapper.toDTO(updated);
    }

}
