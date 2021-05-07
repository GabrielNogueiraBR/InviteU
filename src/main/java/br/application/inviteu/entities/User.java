package br.application.inviteu.entities;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.application.inviteu.dto.user.UserCreateDTO;
import br.application.inviteu.dto.user.UserUpdateDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(of="id")
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private String name;
    @Getter @Setter private LocalDate birthDate;
    @Getter @Setter private String rg;
    @Getter @Setter private String cpf;
    @Getter @Setter private String email;
    @Getter @Setter private String gender;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @Getter @Setter private List<Rating> ratingList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter @Setter private Collection<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Getter @Setter private Address address;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_owner_id")
    @Getter @Setter private List<Event> events;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "user_sub_event",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "subEvent")
    )
    @Getter @Setter private List<SubEvent> subEvents;
    
    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
      
    public User(String username, String password, String name, LocalDate birthDate, String rg, String cpf, String email,
            String gender, Collection<Role> roles, Address address) {
        this.username = username;
        setPassword(password);
        this.name = name;
        this.birthDate = birthDate;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.gender = gender;
        this.roles = roles;
        this.address = address;
    }

    public User(UserCreateDTO userCreateDTO){
        this.username = userCreateDTO.getUsername();
        setPassword(userCreateDTO.getPassword());
        this.name = userCreateDTO.getName();
        this.birthDate = userCreateDTO.getBirthDate();
        this.rg = userCreateDTO.getRg();
        this.cpf = userCreateDTO.getCpf();
        this.email = userCreateDTO.getEmail();
        this.gender = userCreateDTO.getGender();
        this.roles = userCreateDTO.getRoles();
        this.address = userCreateDTO.getAddress();
    }

    public void setUserToUpdate(UserUpdateDTO userUpdateDTO) {
        this.username = userUpdateDTO.getUsername();
        setPassword(userUpdateDTO.getPassword());
        this.name = userUpdateDTO.getName();
        this.email = userUpdateDTO.getEmail();
        this.address = userUpdateDTO.getAddress();
    }

}
