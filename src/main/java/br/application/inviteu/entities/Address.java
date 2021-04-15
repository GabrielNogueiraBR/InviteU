package br.application.inviteu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Address")
public class Address implements Serializable{

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(mappedBy = "address")
   @JoinColumn(name = "event_id", referencedColumnName = "id")
   private Event event;

   private String zipCode;
   private String state;
   private String city;
   private String district;
   private String street;
   private String number;

}
