package br.application.inviteu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.application.inviteu.dto.role.RoleCreateDTO;
import br.application.inviteu.dto.role.RoleDTO;
import br.application.inviteu.entities.Role;
import br.application.inviteu.repositories.RoleRepository;

@Service
public class RoleService {
   @Autowired
   private RoleRepository roleRepository;

   public List<RoleDTO> getRoles() {
      List<Role> listRoles = roleRepository.findAll();

      if (listRoles.isEmpty()) {
         throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Roles to be shown.");
      }
      return toListDTO(listRoles);
  }

  public RoleDTO getRoleById(Long id){
      Optional<Role> opRole = roleRepository.findById(id);

      Role role = opRole.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Roles with this Id to shown."));

      return new RoleDTO(role);
  }

  public RoleDTO createRole(RoleCreateDTO newRoleDTO){
      Role role = new Role(newRoleDTO);

      try {
         role = roleRepository.save(role);
         return new RoleDTO(role);
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error when saving the Role on the database");
      }
  }

  public RoleDTO updateRole(Long id, RoleCreateDTO roleUpdateDTO){
      try{
         Optional<Role> opRole = roleRepository.findById(id);
         Role role = opRole.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Roles with this Id."));
         role = roleRepository.getOne(id);
         role.setRole(roleUpdateDTO.getRole());
         role.setUsers(roleUpdateDTO.getUsers());
         role = roleRepository.save(role);
         return new RoleDTO(role);
      }
      catch(EntityNotFoundException e){
         throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Roles with this Id to shown.");
      }
  }

  public void removeRole(Long id){
      try{
         roleRepository.deleteById(id);
         throw new ResponseStatusException(HttpStatus.OK, "The Role has been deleted");
      }
      catch(EmptyResultDataAccessException e){
         throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Roles with this Id to shown.");
      }
  }

  private List<RoleDTO> toListDTO(List<Role> roles) {
      List<RoleDTO> listDTO = new ArrayList<>();

      for (Role role : roles) {
          listDTO.add(new RoleDTO(role));
      }
      return listDTO;
  }
}
