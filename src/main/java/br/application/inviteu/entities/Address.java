package br.application.inviteu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "Address")
public class Address implements Serializable{

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Getter private Long id;

   @JsonIgnore
   @OneToOne(mappedBy = "address")
   @JoinColumn(name = "event_id", referencedColumnName = "id")
   @Getter @Setter private Event event;

   @Getter @Setter private String zipCode;
   @Getter @Setter private String state;
   @Getter @Setter private String city;
   @Getter @Setter private String district;
   @Getter @Setter private String street;
   @Getter @Setter private String number;
   
   public Address(String zipCode, String state, String city, String district, String street, String number) {
      this.zipCode = zipCode;
      this.state = state;
      this.city = city;
      this.district = district;
      this.street = street;
      this.number = number;
   } 

}
