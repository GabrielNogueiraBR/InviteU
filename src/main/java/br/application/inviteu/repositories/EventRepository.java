package br.application.inviteu.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.application.inviteu.entities.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
