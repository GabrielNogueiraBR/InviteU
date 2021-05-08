package br.application.inviteu.dto;

import br.application.inviteu.entities.Status;
import lombok.*;

@Data
public class StatusInsertDTO {

    String description;

    public Status toStatus() {
        return new Status(description);
    }
}
