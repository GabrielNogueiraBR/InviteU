package br.application.inviteu.entities;

import java.io.Serializable;

import javax.persistence.*;

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

   public Address() {
   }

   public Address(String zipCode, String state, String city, String district, String street, String number) {
      this.zipCode = zipCode;
      this.state = state;
      this.city = city;
      this.district = district;
      this.street = street;
      this.number = number;
   }

   public Long getId() {
      return id;
   }

   public String getZipCode() {
      return zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getDistrict() {
      return district;
   }

   public void setDistrict(String district) {
      this.district = district;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Address other = (Address) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }

}
