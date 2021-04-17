package br.application.inviteu.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable{

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true)
   private String role;
   
   @JsonIgnore
   @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
   private Collection<User> users;

   public Role(String role) {
      this.role = role;
   }
}
