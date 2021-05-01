package br.application.inviteu.dto.user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import br.application.inviteu.entities.Address;
import br.application.inviteu.entities.Event;
import br.application.inviteu.entities.Role;
import br.application.inviteu.entities.User;
import lombok.Data;

@Data
public class UserDTO {

   private Long id;
   private String username;
   private String name;
   private LocalDate birthDate;
   private String rg;
   private String cpf;
   private String email;
   private String gender;
   private Collection<Role> roles;
   private Address address;
   private List<Event> events;

   public UserDTO(User user) {
      this.id = user.getId();
      this.username = user.getUsername();
      this.name = user.getName();
      this.birthDate = user.getBirthDate();
      this.rg = user.getRg();
      this.cpf = user.getCpf();
      this.email = user.getEmail();
      this.gender = user.getGender();
      this.roles = user.getRoles();
      this.address = user.getAddress();
      this.events = user.getEvents();
   }
   
}
