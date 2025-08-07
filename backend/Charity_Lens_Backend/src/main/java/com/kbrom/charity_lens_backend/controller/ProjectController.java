package com.kbrom.charity_lens_backend.controller;

import com.kbrom.charity_lens_backend.dto.CreateProjectDTO;
import com.kbrom.charity_lens_backend.dto.GetProjectDTO;
import com.kbrom.charity_lens_backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @PostMapping
    public ResponseEntity<GetProjectDTO> createProject(@RequestBody CreateProjectDTO createProjectDTO){
        GetProjectDTO project =projectService.createProject(createProjectDTO);
        return ResponseEntity.ok(project);
    }
    @GetMapping
    public ResponseEntity<List<GetProjectDTO>> getAllProjects() {  // needs pagination + filtering later
        List<GetProjectDTO> projects= projectService.findAllProjects();
        return ResponseEntity.ok(projects);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetProjectDTO> getProjectById(@PathVariable Long id) {
        GetProjectDTO project= projectService.findProjectById(id);
        return ResponseEntity.ok(project);
    }
    @GetMapping("/by-organization/{organizationId}")  // needs pagination + filtering later
    public ResponseEntity<List<GetProjectDTO>> findProjectsByOrgId(@PathVariable Long organizationId) {
        List<GetProjectDTO> projects= projectService.findProjectsByOrganizationId(organizationId);
        return ResponseEntity.ok(projects);
    }
    @PatchMapping("/{id}/terminate")
    public ResponseEntity<String> terminateProject(@PathVariable Long id) {
       return ResponseEntity.ok().body(projectService.terminateProjectById(id));
    }


}
