package br.cinveste.controller;

import br.cinveste.model.UserEntity;
import br.cinveste.record.TeamDto;
import br.cinveste.response.TeamResponseDto;
import br.cinveste.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/register")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TeamResponseDto> createTeam(@RequestBody TeamDto dto,
                                                      @AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(teamService.createTeam(dto, currentUser));
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> listTeams() {
        return ResponseEntity.ok(teamService.listTeams());
    }
}
