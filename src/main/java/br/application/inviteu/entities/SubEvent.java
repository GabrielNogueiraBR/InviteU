package br.application.inviteu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
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
    private LocalDateTime dateTime;
    private Boolean isLimited;
    private Integer capacity;

    @OneToMany(mappedBy = "subEvent")
    private List<Rating> rating;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

}
