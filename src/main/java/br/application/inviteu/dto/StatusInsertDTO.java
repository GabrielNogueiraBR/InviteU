package br.application.inviteu.dto;

import br.application.inviteu.entities.Status;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StatusInsertDTO {
    @Getter @Setter String description;

    public Status toStatus(){
        return new Status(description);
    }
}
