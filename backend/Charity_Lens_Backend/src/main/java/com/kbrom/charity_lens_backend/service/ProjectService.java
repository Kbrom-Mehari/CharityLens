package com.kbrom.charity_lens_backend.service;

import com.kbrom.charity_lens_backend.dto.ProjectDTO;
import com.kbrom.charity_lens_backend.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.model.CharityOrganization;
import com.kbrom.charity_lens_backend.model.Project;
import com.kbrom.charity_lens_backend.repository.CharityOrganizationRepository;
import com.kbrom.charity_lens_backend.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final CharityOrganizationRepository charityOrganizationRepository;

    public Project createProject(ProjectDTO projectDTO) {
        CharityOrganization org=charityOrganizationRepository.findById(projectDTO.getOrganizationId()).orElseThrow(()-> new ResourceNotFoundException("Organization not found"));
        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setDonationGoal(projectDTO.getDonationGoal());
        project.setCause(projectDTO.getCause());
        project.setOrganization(org);
        return projectRepository.save(project);
    }
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
    public List<Project> findActiveProjects() {
        return projectRepository.findAllByActive(true);
    }
    public Project findProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Project not found"));
    }
    public List<Project> findProjectsByOrganizationId(Long id) {
        return projectRepository.findAllByOrganizationId(id);
    }
    @Transactional      // to
    public String terminateProjectById(Long id) {
        Project project = findProjectById(id);
        if (!project.isActive()) {
            return "Project is already terminated";
        }
        project.setActive(false);
        return "Project terminated";
    }


}
