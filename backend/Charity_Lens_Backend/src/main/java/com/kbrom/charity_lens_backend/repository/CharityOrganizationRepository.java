package com.kbrom.charity_lens_backend.repository;

import com.kbrom.charity_lens_backend.model.CharityOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharityOrganizationRepository extends JpaRepository<CharityOrganization, Long> {
    Optional<CharityOrganization> findByEmail(String email);
    List<CharityOrganization> searchCharityOrganizationByNameContainingIgnoreCase(String name);


}
