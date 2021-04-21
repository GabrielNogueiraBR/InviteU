package br.application.inviteu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import br.application.inviteu.dto.event.EventDTO;
import br.application.inviteu.dto.rating.RatingUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.application.inviteu.dto.rating.RatingCreateDTO;
import br.application.inviteu.dto.rating.RatingDTO;
import br.application.inviteu.entities.Rating;
import br.application.inviteu.repositories.RatingRepository;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<RatingDTO> getAllRatings() {
        List<Rating> listRatings = ratingRepository.findAll();

        if (listRatings.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No ratings to be shown.");
        }
        return toListDTO(listRatings);
    }

    public RatingDTO getRatingById(Long id) {
        Optional<Rating> opRating = ratingRepository.findById(id);

        Rating rating = opRating.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found"));

        return new RatingDTO(rating);
    }

    public RatingDTO createRating(RatingCreateDTO newRatingDto) {
        Rating ratingEntity = new Rating(newRatingDto);

        try {
            ratingEntity = ratingRepository.save(ratingEntity);
            return new RatingDTO((ratingEntity));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error when saving the event on the database");
        }
    }

    public RatingDTO updateRating(Long id, RatingUpdateDTO ratingUpdateDto) {
        try {
            Rating rating = ratingRepository.getOne(id);

            rating.setRating(ratingUpdateDto.getRating());

            rating = ratingRepository.save(rating);

            return new RatingDTO(rating);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ratings with this Id to be shown.");
        }
    }

    public void removeRating(Long id) {
        try {
            ratingRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ratings with this Id to be shown.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rating could not be deleted");
        }
    }

    private List<RatingDTO> toListDTO(List<Rating> ratings) {
        List<RatingDTO> listDTO = new ArrayList<>();

        for (Rating rating : ratings) {
            listDTO.add(new RatingDTO(rating));
        }
        return listDTO;
    }

}