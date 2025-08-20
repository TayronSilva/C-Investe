package br.cinveste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.cinveste.model.EntrepreneurEntity;

public interface EntrepreneurRepository extends JpaRepository<EntrepreneurEntity, Integer> {

}