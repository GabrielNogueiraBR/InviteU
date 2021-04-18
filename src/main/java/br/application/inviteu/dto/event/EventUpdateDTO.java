package br.application.inviteu.dto.event;

import br.application.inviteu.entities.Address;
import lombok.Data;

@Data
public class EventUpdateDTO {
    private String title;
    private String description;
    private Boolean isPublic;
    private Address address;
}
