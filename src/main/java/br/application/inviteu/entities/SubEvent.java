package br.application.inviteu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Entity
@Table(name = "Event_sub")
public class SubEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isLimited;
    private Integer capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "subEvent")
    private List<Rating> ratingList;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToMany(mappedBy = "subEvents")
    private List<User> user;

    @JsonGetter("rating")
    public Double getRatingValue(){
        int quantity = 0;
        Double rantingSum = 0.0;

        for (Rating element : ratingList) {
            rantingSum += element.getRating();
            quantity++;
        }

        if(quantity == 0)
            return 0.0;
        
        return (rantingSum/quantity);
    }

}
