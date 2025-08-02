package com.kbrom.charity_lens_backend.controller;

import com.kbrom.charity_lens_backend.dto.GetProjectDTO;
import com.kbrom.charity_lens_backend.model.Project;
import com.kbrom.charity_lens_backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping
    public List<GetProjectDTO> getAllProjects() {
        return projectService.findAllProjects();
    }
    @GetMapping("{id}")
    public GetProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.findProjectById(id);
    }
    @GetMapping()
    public List<GetProjectDTO> findProjectsByOrgId(@RequestParam Long organizationId) {
        return projectService.findProjectsByOrganizationId(organizationId);
    }
}
