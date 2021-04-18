package br.application.inviteu.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    @Getter @Setter private User owner;

    @OneToMany(mappedBy = "event")
    @Getter @Setter private List<SubEvent> subEvents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Getter @Setter private Address address;

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

}
