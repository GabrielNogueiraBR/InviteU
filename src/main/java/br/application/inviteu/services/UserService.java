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

import br.application.inviteu.dto.user.UserCreateDTO;
import br.application.inviteu.dto.user.UserDTO;
import br.application.inviteu.dto.user.UserUpdateDTO;
import br.application.inviteu.entities.User;
import br.application.inviteu.repositories.UserRepository;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   public List<UserDTO> getUsers() {
      List<User> listUsers = userRepository.findAll();

      if (listUsers.isEmpty())
         throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Users to be shown.");

      return toListDTO(listUsers);
  }

  public User getUserById(Long id){
     Optional<User> opUser = userRepository.findById(id);

     return opUser.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Users with this Id to shown."));
  }

  public UserDTO getUserByIdDTO(Long id){
      Optional<User> opUser = userRepository.findById(id);

      User user = opUser.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Users with this Id to shown."));

      return new UserDTO(user);
  }

  public UserDTO createUser(UserCreateDTO newUserDTO){
      User user = new User(newUserDTO);

      try {
         user = userRepository.save(user);
         return new UserDTO(user);
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error when saving the User on the database");
      }
  }

  public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO){
      try{
         Optional<User> opUser = userRepository.findById(id);
         User user = opUser.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Users with this Id."));
         user = userRepository.getOne(id);
         user.setUserToUpdate(userUpdateDTO);
         user = userRepository.save(user);
         return new UserDTO(user);
      }
      catch(EntityNotFoundException e){
         throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Users with this Id to shown.");
      }
  }

  public void removeUser(Long id){
      try{
         userRepository.deleteById(id);
         throw new ResponseStatusException(HttpStatus.OK, "The User has been deleted");
      }
      catch(EmptyResultDataAccessException e){
         throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Users with this Id to shown.");
      }
  }

  private List<UserDTO> toListDTO(List<User> users) {
      List<UserDTO> listDTO = new ArrayList<>();

      for (User user : users) {
          listDTO.add(new UserDTO(user));
      }
      return listDTO;
  }



}
