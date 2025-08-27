package br.cinveste.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.cinveste.record.TeamDto;
import br.cinveste.service.TeamService;
import br.cinveste.response.TeamResponseDto;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/create")
    public TeamResponseDto createTeam(@RequestBody TeamDto teamDto) {
        return teamService.createTeam(teamDto);
    }
    
}

