package br.application.inviteu.dto.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.application.inviteu.entities.Address;
import br.application.inviteu.entities.Role;
import lombok.Data;

@Data
public class UserCreateDTO {

   private String username;
   private String password;
   private String name;
   private LocalDate birthDate;
   private String rg;
   private String cpf;
   private String email;
   private String gender;
   private ArrayList<Role> roles;
   private Address address;

}
