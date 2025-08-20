package br.cinveste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.cinveste.model.InvestorEntity;

public interface InvestorRepository extends JpaRepository<InvestorEntity, Integer> {
    
}