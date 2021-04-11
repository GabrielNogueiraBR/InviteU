package br.application.inviteu.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Event_sub")
public class SubEvent {

    public SubEvent() {
    }

    public SubEvent(Long id, String title, String description, LocalDateTime dateTime, Boolean isLimited, Number capacity, Event event) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.isLimited = isLimited;
        this.capacity = capacity;
        this.event = event;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime dateTime;
    private Boolean isLimited;
    private Number capacity;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

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

    public Number getCapacity() {
        return capacity;
    }

    public void setCapacity(Number capacity) {
        this.capacity = capacity;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
