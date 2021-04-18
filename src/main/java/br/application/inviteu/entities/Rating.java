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
@Table(name = "Rating")
public class Rating implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter @Setter private Integer rating;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Getter @Setter private User user;

    @OneToOne()
    @JoinColumn(name = "subEvent_id", referencedColumnName = "id")
    @Getter @Setter private SubEvent subEvent;
    
}
