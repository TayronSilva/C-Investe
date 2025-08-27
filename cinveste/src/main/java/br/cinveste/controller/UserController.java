package br.cinveste.controller;
import br.cinveste.service.UserService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cinveste.enums.UserType;
import br.cinveste.model.UserEntity;
import br.cinveste.record.RegisterDto;
import br.cinveste.response.UserResponseDto;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/save")
    public UserEntity salvarUsuario(@RequestBody RegisterDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/list")
    public List<UserResponseDto> listUsers() {
        return userService.listUsers();
    }


    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
    
    @PutMapping("/{id}")
    public UserEntity atualizarUsuario(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
        userEntity.setId(id);
        return userService.saveUser(userEntity);
    }

    @GetMapping("/type/{userType}")
    public Optional<UserEntity> getUserByType(@PathVariable("userType") String userType) {
        UserType type = UserType.valueOf(userType);
        return userService.getUserType(type);
}


}
