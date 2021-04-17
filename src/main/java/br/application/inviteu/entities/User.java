package br.application.inviteu.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private LocalDate birthDate;
    private String rg;
    private String cpf;
    private String email;
    private String gender;

    public User(String username, String password, String name, LocalDate birthDate, String rg, String cpf, String email, String gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.gender = gender;
        this.address = address;
    }

    @OneToMany(mappedBy = "user")
    private List<Rating> ratingList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
