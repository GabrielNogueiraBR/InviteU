package br.application.inviteu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.application.inviteu.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{
    
}
