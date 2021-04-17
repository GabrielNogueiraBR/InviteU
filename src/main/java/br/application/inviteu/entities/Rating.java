package br.application.inviteu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Table(name = "Rating")
public class Rating implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne()
    @JoinColumn(name = "subEvent_id", referencedColumnName = "id")
    private SubEvent subEvent;
    
}
