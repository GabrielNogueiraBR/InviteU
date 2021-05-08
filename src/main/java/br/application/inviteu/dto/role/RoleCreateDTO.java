package br.application.inviteu.dto.role;

import java.util.List;

import br.application.inviteu.entities.User;
import lombok.Data;

@Data
public class RoleCreateDTO {
   private String role;
   private List<User> users;
}
