package br.cinveste.service;

import br.cinveste.record.ProjectDto;
import br.cinveste.model.ProjectEntity;
import br.cinveste.model.TeamEntity;
import br.cinveste.repository.ProjectRepository;
import br.cinveste.repository.TeamRepository;
import br.cinveste.response.ProjectResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamRepository teamRepository;

    // Criar projeto
    public ProjectResponseDto createProject(ProjectDto projectDto) {
        TeamEntity team = teamRepository.findById(projectDto.idProjeto())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        ProjectEntity project = new ProjectEntity();
        project.setNome(projectDto.nome());
        project.setArea(projectDto.area());
        project.setDescricao(projectDto.descricao());
        project.setOrientador(projectDto.orientador());
        project.setVpl(projectDto.vpl());
        project.setRoi(projectDto.roi());
        project.setTir(projectDto.tir());
        project.setPayback(projectDto.payback());
        project.setPaybackDescontado(projectDto.paybackDescontado());
        project.setPeriodo(projectDto.periodo());
        project.setMercadoAlvo(projectDto.mercadoAlvo());
        project.setTeam(team);

        ProjectEntity saved = projectRepository.save(project);

        return new ProjectResponseDto(
                saved.getIdProjeto(),
                saved.getNome(),
                saved.getArea(),
                saved.getDescricao(),
                saved.getOrientador(),
                saved.getVpl(),
                saved.getRoi(),
                saved.getTir(),
                saved.getPayback(),
                saved.getPaybackDescontado(),
                saved.getPeriodo(),
                saved.getMercadoAlvo(),
                team.getNome()
        );
    }

    // Listar todos os projetos
    public List<ProjectResponseDto> listProjects() {
        return projectRepository.findAll().stream()
                .map(project -> new ProjectResponseDto(
                        project.getIdProjeto(),
                        project.getNome(),
                        project.getArea(),
                        project.getDescricao(),
                        project.getOrientador(),
                        project.getVpl(),
                        project.getRoi(),
                        project.getTir(),
                        project.getPayback(),
                        project.getPaybackDescontado(),
                        project.getPeriodo(),
                        project.getMercadoAlvo(),
                        project.getTeam().getNome()
                ))
                .toList();
    }

    // Buscar projeto por ID
    public ProjectResponseDto getProjectById(Integer id) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return new ProjectResponseDto(
                project.getIdProjeto(),
                project.getNome(),
                project.getArea(),
                project.getDescricao(),
                project.getOrientador(),
                project.getVpl(),
                project.getRoi(),
                project.getTir(),
                project.getPayback(),
                project.getPaybackDescontado(),
                project.getPeriodo(),
                project.getMercadoAlvo(),
                project.getTeam().getNome()
        );
    }

    // Deletar projeto
    public void deleteProject(Integer id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
