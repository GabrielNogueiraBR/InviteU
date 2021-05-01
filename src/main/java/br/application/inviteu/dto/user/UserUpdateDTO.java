package br.application.inviteu.dto.user;

import br.application.inviteu.entities.Address;
import lombok.Data;

@Data
public class UserUpdateDTO {

   private String username;
   private String password;
   private String name;
   private String email;
   private Address address;

}
