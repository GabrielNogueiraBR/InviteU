package br.application.inviteu.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    public SubEvent() {
    }

    public SubEvent(Long id, String title, String description, LocalDateTime dateTime, Boolean isLimited, Integer capacity, List<Rating> rating, Event event, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.isLimited = isLimited;
        this.capacity = capacity;
        this.rating = rating;
        this.event = event;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getLimited() {
        return isLimited;
    }

    public void setLimited(Boolean limited) {
        isLimited = limited;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubEvent subEvent = (SubEvent) o;
        return Objects.equals(id, subEvent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
