package br.application.inviteu.controllers;

import java.net.URI;
import java.util.List;

import br.application.inviteu.dto.rating.RatingUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.application.inviteu.dto.rating.RatingCreateDTO;
import br.application.inviteu.dto.rating.RatingDTO;
import br.application.inviteu.entities.Rating;
import br.application.inviteu.services.RatingService;

@RestController
@RequestMapping("api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/all")
    public ResponseEntity<List<RatingDTO>> getAllRatings() {
        List<RatingDTO> listRatingDto = ratingService.getAllRatings();
        return ResponseEntity.ok(listRatingDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDTO> getRatingById(@PathVariable("id") Long id) {
        RatingDTO ratingDto = ratingService.getRatingById(id);
        return ResponseEntity.ok(ratingDto);
    }

    @PostMapping("/new")
    public ResponseEntity<RatingDTO> saveRating(@RequestBody RatingCreateDTO createDto) {
        RatingDTO ratingDto = ratingService.createRating(createDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ratingDto.getId()).toUri();
        return ResponseEntity.created(uri).body(ratingDto);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable("id") Long id, @RequestBody RatingUpdateDTO updateDto) {
        RatingDTO ratingDto = ratingService.updateRating(id, updateDto);
        return ResponseEntity.ok(ratingDto);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> deleteRating(@PathVariable("id") Long id) {
        ratingService.removeRating(id);
        return ResponseEntity.noContent().build();
    }
}