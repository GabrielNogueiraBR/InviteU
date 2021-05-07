package br.application.inviteu.controllers;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.application.inviteu.dto.event.EventCreateDTO;
import br.application.inviteu.dto.event.EventDTO;
import br.application.inviteu.dto.event.EventUpdateDTO;
import br.application.inviteu.dto.user.UserCreateDTO;
import br.application.inviteu.dto.user.UserDTO;
import br.application.inviteu.dto.user.UserUpdateDTO;
import br.application.inviteu.entities.User;
import br.application.inviteu.services.EventService;
import br.application.inviteu.services.SubEventService;
import br.application.inviteu.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
   
   @Autowired
   private UserService userService;

   @Autowired
    private EventService eventService;

   @Autowired
   private SubEventService subEventService;

   @GetMapping("/all")
   public ResponseEntity<List<UserDTO>> getUsers() {
      List<UserDTO> listUsers = userService.getUsers();
      return ResponseEntity.ok(listUsers);
   }

   @GetMapping("/{id}")
   public ResponseEntity<UserDTO> getUserByIdDTO(@PathVariable("id") Long id){
      UserDTO userDto = userService.getUserByIdDTO(id);
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

   @PostMapping("/{id}/events/new")
   public ResponseEntity<EventDTO> createEvent(@RequestBody EventCreateDTO createDto, @PathVariable("id") Long id) {
      User user = userService.getUserById(id);
      EventDTO eventDto = eventService.createEvent(createDto,user);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDto.getId()).toUri();
      return ResponseEntity.created(uri).body(eventDto);
   }

   @PutMapping("/{idUser}/events/{idEvent}/edit")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable("idEvent") Long idEvent, @RequestBody EventUpdateDTO updateEventDto, @PathVariable("idUser") Long idUser) {
        EventDTO eventDto = eventService.updateEvent(idEvent, updateEventDto,idUser);
        return ResponseEntity.ok().body(eventDto);
    }

   @DeleteMapping("/{idUser}/events/{idEvent}/delete")
   public ResponseEntity<Void> removeEvent(@PathVariable("idEvent") Long idEvent, @PathVariable("idUser") Long idUser) {
      eventService.removeEvent(idEvent,idUser);
      return ResponseEntity.noContent().build();
   }
   
}
