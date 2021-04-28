package br.application.inviteu.entities;

import br.application.inviteu.dto.event.EventCreateDTO;
import br.application.inviteu.dto.event.EventDTO;
import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "Event_main")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Getter @Setter private Boolean isPublic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Getter @Setter private Address address;

    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    @Getter @Setter private User owner;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @Getter @Setter private List<SubEvent> subEvents;
    
    @JsonGetter("rating")
    public Double getRatingValue(){
        int quantity = 0;
        Double average = 0.0;

        for(SubEvent subEvent: subEvents){
            average += subEvent.getRatingValue();
            quantity++;
        }

        if(quantity == 0)
            return 0.0;
        
        average = average/quantity;
        return average;
    }

    public Event(EventCreateDTO newEventDto) {
        this.title = newEventDto.getTitle();
        this.description = newEventDto.getDescription();
        this.isPublic = newEventDto.getIsPublic();
        this.address = newEventDto.getAddress();
        this.owner = newEventDto.getOwner();
    }

    public Event(EventDTO eventDto) {
        this.id = eventDto.getId();
        this.title = eventDto.getTitle();
        this.description = eventDto.getDescription();
        this.isPublic = eventDto.getIsPublic();
        this.address = eventDto.getAddress();
        this.owner = eventDto.getOwner();
        this.subEvents = eventDto.getSubEvents();
    }

}
