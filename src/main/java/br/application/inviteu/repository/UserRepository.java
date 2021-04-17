package br.application.inviteu.repository;

import org.springframework.data.repository.CrudRepository;

import br.application.inviteu.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
   User findByUsername(String username);
}
