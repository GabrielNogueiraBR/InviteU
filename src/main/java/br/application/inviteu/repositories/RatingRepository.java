package br.application.inviteu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.application.inviteu.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
