package br.application.inviteu.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(mappedBy = "endereco")
   private Usuario usuario;

   private String cep;
   private String estado;
   private String cidade;
   private String bairro;
   private String rua;
   private String numero;

   public Endereco() {

   }

   public Endereco(Usuario usuario, String cep, String estado, String cidade, String bairro, String rua, String numero) {
      this.usuario = usuario;
      this.cep = cep;
      this.estado = estado;
      this.cidade = cidade;
      this.bairro = bairro;
      this.rua = rua;
      this.numero = numero;
   }

   public Long getId() {
      return id;
   }

   public String getCep() {
      return cep;
   }

   public void setCep(String cep) {
      this.cep = cep;
   }

   public String getEstado() {
      return estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public String getCidade() {
      return cidade;
   }

   public void setCidade(String cidade) {
      this.cidade = cidade;
   }

   public String getBairro() {
      return bairro;
   }

   public void setBairro(String bairro) {
      this.bairro = bairro;
   }

   public String getRua() {
      return rua;
   }

   public void setRua(String rua) {
      this.rua = rua;
   }

   public String getNumero() {
      return numero;
   }

   public void setNumero(String numero) {
      this.numero = numero;
   }

   public Usuario getUsuario() {
      return usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

}
