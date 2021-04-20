package br.application.inviteu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.application.inviteu.entities.SubEvent;

@Repository
public interface SubEventRepository extends JpaRepository< SubEvent, Long> {
    
}
