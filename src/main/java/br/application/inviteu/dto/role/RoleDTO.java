package br.application.inviteu.dto.role;

import java.util.List;

import br.application.inviteu.entities.Role;
import br.application.inviteu.entities.User;
import lombok.Data;

@Data
public class RoleDTO {
   private Long id;
   private String role;
   private List<User> users;

   public RoleDTO(Role role) {
      this.id = role.getId();
      this.role = role.getRole();
      this.users = role.getUsers();
   }
}
