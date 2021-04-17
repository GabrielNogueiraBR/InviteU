package br.application.inviteu;

import br.application.inviteu.entities.Role;
import br.application.inviteu.entities.User;
import br.application.inviteu.repository.RoleRepository;
import br.application.inviteu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("admin", passwordEncoder.encode("1234"),"Admin", LocalDate.now(), "111.111.11-1", "111.111.233-01","admin@gmail.com","male");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user = new User("user", passwordEncoder.encode("1234"),"User", LocalDate.now(), "111.111.11-1", "111.111.233-01","admin@gmail.com","male");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);
    }
}
