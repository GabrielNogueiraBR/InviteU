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

@AllArgsConstructor
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
   @Getter @Setter private Collection<User> users;

}
