package br.application.inviteu.repositories;

import br.application.inviteu.entities.SubEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubEventRepository extends JpaRepository<SubEvent, Long> {
   
}
