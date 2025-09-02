package br.cinveste.repository;

import br.cinveste.model.UserEntity;
import br.cinveste.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByTipoUsuario(UserType tipoUsuario);
}
