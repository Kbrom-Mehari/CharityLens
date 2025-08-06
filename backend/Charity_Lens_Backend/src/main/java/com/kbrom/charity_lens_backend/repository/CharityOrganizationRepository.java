package com.kbrom.charity_lens_backend.repository;

import com.kbrom.charity_lens_backend.model.CharityOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharityOrganizationRepository extends JpaRepository<CharityOrganization, Long> {
    CharityOrganization findByEmail(String email);
    CharityOrganization findById(long id);
    List<CharityOrganization> searchCharityOrganizationByNameContainingIgnoreCase(String name);


}
