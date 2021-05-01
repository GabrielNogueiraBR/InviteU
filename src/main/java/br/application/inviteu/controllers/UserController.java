package br.application.inviteu.controllers;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.application.inviteu.dto.user.UserCreateDTO;
import br.application.inviteu.dto.user.UserDTO;
import br.application.inviteu.dto.user.UserUpdateDTO;
import br.application.inviteu.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
   
   @Autowired
   private UserService userService;

   @GetMapping("/all")
   public ResponseEntity<List<UserDTO>> getUsers() {
      List<UserDTO> listUsers = userService.getUsers();
      return ResponseEntity.ok(listUsers);
   }

   @GetMapping("/{id}")
   public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
      UserDTO userDto = userService.getUserById(id);
      return ResponseEntity.ok(userDto);
   }

   @PostMapping("/new")
   public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO createDto){
      UserDTO userDto = userService.createUser(createDto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDto.getId()).toUri();
      return ResponseEntity.created(uri).body(userDto);
   }

   @PutMapping("/{id}/edit")
   public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDTO updateUserDto){
      UserDTO userDto = userService.updateUser(id, updateUserDto);
      return ResponseEntity.ok().body(userDto);
   }

   @DeleteMapping("/{id}/delete")
   public ResponseEntity<Void> removeUser(@PathVariable("id") Long id){
      userService.removeUser(id);
      return ResponseEntity.noContent().build();
   }
   
}
