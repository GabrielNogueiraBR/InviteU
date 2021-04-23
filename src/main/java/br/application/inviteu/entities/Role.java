package br.application.inviteu.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.application.inviteu.dto.role.RoleCreateDTO;
import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode(of="id")
@Entity
public class Role implements Serializable{

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Getter private Long id;

   @Column(unique = true)
   @Getter @Setter
   private String role;
   
   @JsonIgnore
   @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
   @Getter @Setter private List<User> users;

   public Role(String role) {
      this.role = role;
   }

   public Role(RoleCreateDTO newRoleDTO) {
      this.role = newRoleDTO.getRole();
      this.users = newRoleDTO.getUsers();
   }

}
