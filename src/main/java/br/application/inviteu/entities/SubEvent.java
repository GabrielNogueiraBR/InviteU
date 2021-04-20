package br.application.inviteu.entities;

import br.application.inviteu.dto.subEvent.SubEventCreateDTO;
import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "Event_sub")
public class SubEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Getter @Setter private LocalDateTime startDateTime;
    @Getter @Setter private LocalDateTime endDateTime;
    @Getter @Setter private Boolean isLimited;
    @Getter @Setter private Integer capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "subEvent", cascade = CascadeType.ALL)
    @Getter @Setter private List<Rating> ratingList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @Getter @Setter private Event event;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @Getter @Setter private Status status;

    @ManyToMany(mappedBy = "subEvents", cascade = CascadeType.ALL)
    @Getter @Setter private List<User> user;

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

    public SubEvent(SubEventCreateDTO newSubEventDto) {
        this.title = newSubEventDto.getTitle();
        this.description = newSubEventDto.getDescription();
        this.startDateTime = newSubEventDto.getStartDateTime();
        this.endDateTime = newSubEventDto.getEndDateTime();
        this.isLimited = newSubEventDto.getIsLimited();
        this.capacity = newSubEventDto.getCapacity();
    }

}
