package com.kbrom.charity_lens_backend.user.charityOrganization;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharityOrganizationRepository extends JpaRepository<CharityOrganization, Long> {
    List<CharityOrganization> searchCharityOrganizationByOrganizationNameContainingIgnoreCase(String name);


}
