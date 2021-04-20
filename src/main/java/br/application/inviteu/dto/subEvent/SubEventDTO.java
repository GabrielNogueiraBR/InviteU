package br.application.inviteu.dto.subEvent;

import java.time.LocalDateTime;

import br.application.inviteu.entities.Status;
import br.application.inviteu.entities.SubEvent;
import lombok.Data;

@Data
public class SubEventDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isLimited;
    private Integer capacity;
    private Status status;

    public SubEventDTO(SubEvent subEventEntity) {
        this.id = subEventEntity.getId();
        this.title = subEventEntity.getTitle();
        this.description = subEventEntity.getDescription();
        this.startDateTime = subEventEntity.getStartDateTime();
        this.endDateTime = subEventEntity.getEndDateTime();
        this.isLimited = subEventEntity.getIsLimited();
        this.capacity = subEventEntity.getCapacity();
        this.status = subEventEntity.getStatus();
        this.status = subEventEntity.getStatus();
    }

}
