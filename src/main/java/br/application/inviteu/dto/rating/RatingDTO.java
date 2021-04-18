package br.application.inviteu.dto.rating;

import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.entities.User;
import lombok.Data;

@Data
public class RatingDTO {

    private Long id;
    private Integer rating;
    private User user;
    private SubEvent subEvent;
}

