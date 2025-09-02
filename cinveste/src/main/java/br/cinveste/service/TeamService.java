package br.cinveste.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.cinveste.record.TeamDto;
import br.cinveste.model.TeamEntity;
import br.cinveste.model.UserEntity;
import br.cinveste.enums.UserType;
import br.cinveste.repository.TeamRepository;
import br.cinveste.response.TeamResponseDto;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamResponseDto createTeam(TeamDto teamDto, UserEntity currentUser) {
        if (currentUser.getTipoUsuario() != UserType.Empreendedor) {
            throw new RuntimeException("Apenas empreendedores podem criar equipes.");
        }

        TeamEntity team = new TeamEntity();
        team.setUser(currentUser);
        team.setLogoUrl(teamDto.logoUrl());
        team.setNome(teamDto.nome());
        team.setDescricao(teamDto.descricao());

        TeamEntity saved = teamRepository.save(team);

        return new TeamResponseDto(
            saved.getIdEquipe(),
            saved.getNome(),
            saved.getDescricao(),
            saved.getLogoUrl(),
            currentUser.getNome()
        );
    }

    public List<TeamResponseDto> listTeams() {
        return teamRepository.findAll().stream()
            .map(team -> new TeamResponseDto(
                team.getIdEquipe(),
                team.getNome(),
                team.getDescricao(),
                team.getLogoUrl(),
                team.getUser().getNome()
            ))
            .toList();
    }
}
