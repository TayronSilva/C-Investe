package br.cinveste.controller;

import br.cinveste.model.UserEntity;
import br.cinveste.record.RegisterDto;
import br.cinveste.response.UserResponseDto;
import br.cinveste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody RegisterDto data) {
        return ResponseEntity.ok(userService.createUser(data));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(userService.getCurrentUser(currentUser));
    }
}
