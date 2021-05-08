package br.application.inviteu.entities;

import lombok.*;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@ToString
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "Status")
public class Status implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter @Setter private String description;

    public Status (String description){
        this.description = description;
    }

}
