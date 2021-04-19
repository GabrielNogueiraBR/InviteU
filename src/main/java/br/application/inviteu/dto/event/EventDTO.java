package br.application.inviteu.dto.event;

import br.application.inviteu.entities.Address;
import br.application.inviteu.entities.Event;
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
    private Address address;
    private User owner;
    private List<SubEvent> subEvent;

    public EventDTO(Event eventEntity) {
        this.id = eventEntity.getId();
        this.title = eventEntity.getTitle();
        this.description = eventEntity.getDescription();
        this.isPublic = eventEntity.getIsPublic();
        this.address = eventEntity.getAddress();
        this.owner = eventEntity.getOwner();
        this.subEvent = eventEntity.getSubEvents();
    }

}
