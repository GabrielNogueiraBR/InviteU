package br.application.inviteu.dto.subEvent;

import java.time.LocalDateTime;

import br.application.inviteu.entities.Status;
import lombok.Data;

@Data
public class SubEventCreateDTO {

    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isLimited;
    private Integer capacity;
    private Status status;

}

