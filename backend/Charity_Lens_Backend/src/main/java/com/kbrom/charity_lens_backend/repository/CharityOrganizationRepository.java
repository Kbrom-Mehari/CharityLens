package com.kbrom.charity_lens_backend.repository;

import com.kbrom.charity_lens_backend.model.CharityOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityOrganizationRepository extends JpaRepository<CharityOrganization, Long> {

}
