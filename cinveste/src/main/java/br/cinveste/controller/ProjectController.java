package br.cinveste.controller;

import br.cinveste.model.UserEntity;
import br.cinveste.record.ProjectDto;
import br.cinveste.response.ProjectResponseDto;
import br.cinveste.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/register")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectDto dto,
                                                            @AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(projectService.createProject(dto, currentUser));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> listProjects() {
        return ResponseEntity.ok(projectService.listProjects());
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProjectResponseDto>> listMyProjects(@AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(projectService.listProjectsByUser(currentUser));
    }
}
