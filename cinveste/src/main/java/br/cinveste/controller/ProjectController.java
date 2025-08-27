package br.cinveste.controller;

import br.cinveste.record.ProjectDto;
import br.cinveste.response.ProjectResponseDto;
import br.cinveste.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Criar projeto
    @PostMapping("/create")
    public ProjectResponseDto createProject(@RequestBody ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    // Listar todos projetos
    @GetMapping("/list")
    public List<ProjectResponseDto> listProjects() {
        return projectService.listProjects();
    }

    // Buscar projeto por ID
    @GetMapping("/{id}")
    public ProjectResponseDto getProjectById(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

    // Deletar projeto
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
    }
}
