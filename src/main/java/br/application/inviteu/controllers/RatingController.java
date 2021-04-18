package br.application.inviteu.controllers;

import java.net.URI;
import java.util.List;

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
@RequestMapping("/rating")
public class RatingController {
    
    @Autowired
    private RatingService service;

    @GetMapping()
    public ResponseEntity<List<RatingDTO>> getAllRating(){
        List<RatingDTO> list = service.getAllRatings();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        Rating rating = service.getRatingById(id);
        return ResponseEntity.ok(rating);
    }
    
    @PostMapping()
    public ResponseEntity<Rating> saveRating(@RequestBody RatingCreateDTO dto){
        Rating rating = service.saveRating(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(rating);
    }

    @PutMapping("{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody RatingCreateDTO dto){
        Rating rating = service.updateRating(id,dto);
        return ResponseEntity.ok(rating);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id){
        service.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}