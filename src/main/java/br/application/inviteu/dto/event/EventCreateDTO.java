package br.application.inviteu.dto.event;


import java.util.List;

import br.application.inviteu.entities.Address;
import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.entities.User;
import lombok.Data;

@Data
public class EventCreateDTO {

    private String title;
    private String description;
    private Boolean isPublic;
    private User owner;
    private List<SubEvent> subEvent;
    private Address address;

}
