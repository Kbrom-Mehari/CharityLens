package com.kbrom.charity_lens_backend.donation.repository;

import com.kbrom.charity_lens_backend.donation.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findDonationsByCampaignId(Long campaignId);
    List<Donation>findDonationsByProjectId(Long projectId);
    List<Donation>findDonationsByDonorId(Long userId);
    List<Donation>findDonationsByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
