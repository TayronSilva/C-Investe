package br.cinveste.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.cinveste.record.ProjectDto;
import br.cinveste.model.ProjectEntity;
import br.cinveste.model.TeamEntity;
import br.cinveste.model.UserEntity;
import br.cinveste.enums.UserType;
import br.cinveste.repository.ProjectRepository;
import br.cinveste.repository.TeamRepository;
import br.cinveste.response.ProjectResponseDto;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamRepository teamRepository;

    public ProjectResponseDto createProject(ProjectDto projectDto, UserEntity currentUser) {
        if (currentUser.getTipoUsuario() != UserType.Empreendedor) {
            throw new RuntimeException("Apenas empreendedores podem criar projetos.");
        }

        TeamEntity team = teamRepository.findByUser(currentUser)
                .orElseThrow(() -> new RuntimeException("Usuário não possui equipe"));

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

    public List<ProjectResponseDto> listProjects() {
        return projectRepository.findAll().stream()
            .map(p -> new ProjectResponseDto(
                p.getIdProjeto(),
                p.getNome(),
                p.getArea(),
                p.getDescricao(),
                p.getOrientador(),
                p.getVpl(),
                p.getRoi(),
                p.getTir(),
                p.getPayback(),
                p.getPaybackDescontado(),
                p.getPeriodo(),
                p.getMercadoAlvo(),
                p.getTeam().getNome()
            ))
            .toList();
    }

    public List<ProjectResponseDto> listProjectsByUser(UserEntity user) {
        return projectRepository.findAll().stream()
            .filter(p -> p.getTeam().getUser().getId().equals(user.getId()))
            .map(p -> new ProjectResponseDto(
                p.getIdProjeto(),
                p.getNome(),
                p.getArea(),
                p.getDescricao(),
                p.getOrientador(),
                p.getVpl(),
                p.getRoi(),
                p.getTir(),
                p.getPayback(),
                p.getPaybackDescontado(),
                p.getPeriodo(),
                p.getMercadoAlvo(),
                p.getTeam().getNome()
            ))
            .toList();
    }
}
