package br.cinveste.repository;

import br.cinveste.model.TeamEntity;
import br.cinveste.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    Optional<TeamEntity> findByUser(UserEntity user);
}
