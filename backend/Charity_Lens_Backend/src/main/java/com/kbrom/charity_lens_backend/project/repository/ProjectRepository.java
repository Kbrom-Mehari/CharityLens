package com.kbrom.charity_lens_backend.project.repository;

import com.kbrom.charity_lens_backend.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByActive(boolean active);
    List<Project> findAllByOrganizationId(Long id);
    @Query("select p from Project p where p.totalDonation>=p.donationGoal")
    List<Project> findAllByDonationGoalAchieved();
}
