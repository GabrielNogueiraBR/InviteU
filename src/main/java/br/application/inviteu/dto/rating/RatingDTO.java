package br.application.inviteu.dto.rating;

import br.application.inviteu.entities.Rating;
import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.entities.User;
import lombok.Data;

@Data
public class RatingDTO {

    private Long id;
    private Integer rating;
    private User user;
    private SubEvent subEvent;

    public RatingDTO(Rating ratingEvent) {
        this.id = ratingEvent.getId();
        this.rating = ratingEvent.getRating();
        this.user = ratingEvent.getUser();
        this.subEvent = ratingEvent.getSubEvent();
    }

}

