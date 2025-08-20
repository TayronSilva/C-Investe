package br.cinveste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cinveste.model.TeamEntity;
import br.cinveste.model.UserEntity;
import br.cinveste.record.TeamDto;
import br.cinveste.repository.UserRepository;
import br.cinveste.repository.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    public TeamEntity createTeam(TeamDto teamDto) {
        UserEntity user = userRepository.findById(teamDto.user_id())
            .orElseThrow(() -> new RuntimeException("User not found"));

        TeamEntity team = new TeamEntity();
        team.setUser(user);
        team.setLogoUrl(teamDto.logoUrl());
        team.setNome(teamDto.nome());
        team.setDescricao(teamDto.descricao());

        return teamRepository.save(team);
    }

    public List<TeamEntity> listTeams() {
        return teamRepository.findAll();
    }

    public Optional<TeamEntity> getTeamById(Integer id) {
        return teamRepository.findById(id);
    }

    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }
}
