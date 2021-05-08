package br.application.inviteu.dto.event;

import br.application.inviteu.entities.Address;
import br.application.inviteu.entities.User;
import lombok.Data;

@Data
public class EventCreateDTO {

    private String title;
    private String description;
    private Boolean isPublic;
    private User owner;
    private Address address;

}
