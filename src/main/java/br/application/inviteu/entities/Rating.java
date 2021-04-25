package br.application.inviteu.entities;

import br.application.inviteu.dto.rating.RatingCreateDTO;
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

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Getter @Setter private User user;

    @OneToOne()
    @JoinColumn(name = "subEvent_id", referencedColumnName = "id")
    @Getter @Setter private SubEvent subEvent;

    public Rating(RatingCreateDTO newRatingDto) {
        this.rating = newRatingDto.getRating();
        this.user = newRatingDto.getUser();
        this.subEvent = newRatingDto.getSubEvent();
    }
    
}
