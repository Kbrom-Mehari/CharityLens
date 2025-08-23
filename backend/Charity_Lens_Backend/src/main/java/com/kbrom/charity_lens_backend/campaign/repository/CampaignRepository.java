package com.kbrom.charity_lens_backend.campaign.repository;

import com.kbrom.charity_lens_backend.campaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query("select c from Campaign c where c.totalDonation>= c.donationGoal") //JPQL
    List<Campaign> findAllByDonationGoalAchieved();

}
