package br.application.inviteu.repository;

import org.springframework.data.repository.CrudRepository;

import br.application.inviteu.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
   Role findByRole(String role);
}
