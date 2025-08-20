package br.cinveste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cinveste.model.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

}
 