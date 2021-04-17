package br.application.inviteu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Entity
@Table(name = "Event_main")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<SubEvent> subEvents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

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
