package br.application.inviteu.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Event_main")
public class Event {

    public Event() {
    }

    public Event(Long id, String title, String description, Boolean isPublic, List<SubEvent> subEvents) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isPublic = isPublic;
        this.subEvents = subEvents;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Boolean isPublic;

    @OneToMany(mappedBy = "event")
    private List<SubEvent> subEvents;

    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
     */

    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
     */

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

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public List<SubEvent> getSubEvents() {
        return subEvents;
    }

    public void setSubEvents(List<SubEvent> subEvents) {
        this.subEvents = subEvents;
    }
}
