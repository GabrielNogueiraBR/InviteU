package br.application.inviteu.dto.event;

import br.application.inviteu.entities.Address;
import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.entities.User;
import lombok.Data;

import java.util.List;



@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean isPublic;
    private User owner;
    private List<SubEvent> subEvent;
    private Address address;

}
