package br.cinveste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import br.cinveste.record.TeamDto;
import br.cinveste.model.TeamEntity;
import br.cinveste.model.UserEntity;
import br.cinveste.enums.UserType;
import br.cinveste.repository.TeamRepository;
import br.cinveste.repository.UserRepository;
import br.cinveste.response.TeamResponseDto;
import br.cinveste.response.UserResponseDto;

@Service
public class TeamService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    public TeamResponseDto createTeam(TeamDto teamDto) {
    UserEntity user = userRepository.findById(teamDto.user_id())
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getTipoUsuario().equals(UserType.Empreendedor)) {
        throw new RuntimeException("Apenas empreendedores podem criar equipes.");
    }

    TeamEntity team = new TeamEntity();
    team.setUser(user);
    team.setLogoUrl(teamDto.logoUrl());
    team.setNome(teamDto.nome());
    team.setDescricao(teamDto.descricao());

    TeamEntity saved = teamRepository.save(team);

    return new TeamResponseDto(
        saved.getIdEquipe(),
        saved.getNome(),
        saved.getDescricao(),
        saved.getLogoUrl(),
        new UserResponseDto(user.getId(), user.getNome(), user.getEmail(), user.getTipoUsuario().name())
    );
}

}
