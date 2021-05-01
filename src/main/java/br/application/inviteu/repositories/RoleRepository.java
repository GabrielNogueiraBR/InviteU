package br.application.inviteu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.application.inviteu.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
   Role findByRole(String role);   
}
