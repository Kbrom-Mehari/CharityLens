package com.kbrom.charity_lens_backend.service;

import com.kbrom.charity_lens_backend.dto.CreateProjectDTO;
import com.kbrom.charity_lens_backend.dto.GetProjectDTO;
import com.kbrom.charity_lens_backend.exception.ResourceNotFoundException;
import com.kbrom.charity_lens_backend.mapper.GetProjectMapper;
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
    private final GetProjectMapper getProjectMapper;

    public GetProjectDTO createProject(CreateProjectDTO projectDTO) {
        CharityOrganization org=charityOrganizationRepository.findById(projectDTO.getOrganizationId()).orElseThrow(()-> new ResourceNotFoundException("Organization not found"));
        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setDonationGoal(projectDTO.getDonationGoal());
        project.setCause(projectDTO.getCause());
        project.setOrganization(org);
        projectRepository.save(project);
        return getProjectMapper.toDTO(project);
    }
    public List<GetProjectDTO> findAllProjects() {
        List<Project> projects= projectRepository.findAll();
        return getProjectMapper.toDTO_List(projects);
    }
    public List<GetProjectDTO> findActiveProjects() {      // needs pagination later here
        List<Project> projects= projectRepository.findAllByActive(true);
        return getProjectMapper.toDTO_List(projects);
    }
    public GetProjectDTO findProjectById(Long id) {
        Project project=projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Project not found"));
        return getProjectMapper.toDTO(project);
    }
    public List<GetProjectDTO> findProjectsByOrganizationId(Long id) {
        List<Project> projects= projectRepository.findAllByOrganizationId(id);
        return getProjectMapper.toDTO_List(projects);
    }

    @Transactional      // to persist the change automatically when method is called
    public String terminateProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Project not found"));
        if (!project.isActive()) {
            return "Project is already terminated";
        }
        project.setActive(false);
        return "Project terminated";
    }
    public List<GetProjectDTO> getFullyFundedProjects() {    //needs pagination here
        List<Project> projects=projectRepository.findAllByDonationGoalAchieved();
        return getProjectMapper.toDTO_List(projects);
    }
}
