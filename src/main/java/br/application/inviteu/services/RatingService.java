package br.application.inviteu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
        List <Rating> listRatings = ratingRepository.findAll();

        if(listRatings.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "");
        }
        return toListDTO(listRatings);
    }

    public Rating getRatingById(Long id) {
        Optional<Rating> op = ratingRepository.findById(id);
        return op.orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found"));    
    }

    public Rating saveRating(RatingCreateDTO rating) {
        return ratingRepository.save(fromDTO(rating));
    }

    public Rating updateRating(Long id, RatingCreateDTO dto) {
        try{
            Rating rating = getRatingById(id);
            rating.setRating(dto.getRating());
            return ratingRepository.save(rating);
        }
        catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found");
        }
    }

    public void deleteRating(Long id) {
        try{
            ratingRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found");
        }
        catch(DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rating could not be deleted");
        }
    }

    private List<RatingDTO> toListDTO(List<Rating> ratings) {
        List<RatingDTO> listDTO = new ArrayList<>();

        for (Rating rating: ratings) {
            listDTO.add(toDTO(rating));
        }
        return listDTO;
    }

    private RatingDTO toDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();

        ratingDTO.setId(rating.getId());
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setSubEvent(rating.getSubEvent());
        ratingDTO.setUser(rating.getUser());

        return ratingDTO;
    }

    private Rating fromDTO(RatingCreateDTO ratingCreateDTO){
        Rating rating = new Rating();

        ratingCreateDTO.setRating(rating.getRating());
        ratingCreateDTO.setSubEvent(rating.getSubEvent());
        ratingCreateDTO.setUser(rating.getUser());

        return rating;
    }
    
}