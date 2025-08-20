package br.cinveste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.cinveste.model.UserEntity;
import br.cinveste.enums.UserType;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

    Optional<UserEntity> findByTipoUsuario(UserType tipoUsuario);

}
