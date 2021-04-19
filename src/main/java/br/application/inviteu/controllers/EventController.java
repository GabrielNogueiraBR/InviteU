package br.application.inviteu.controllers;

import br.application.inviteu.dto.event.EventCreateDTO;
import br.application.inviteu.dto.event.EventDTO;
import br.application.inviteu.dto.event.EventUpdateDTO;
import br.application.inviteu.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> listEvents = eventService.getAllEvents();
        return ResponseEntity.ok(listEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventsById(@PathVariable("id") Long id){
        EventDTO eventDto = eventService.getEventsById(id);
        return ResponseEntity.ok(eventDto);
    }

    @PostMapping("/new")
    public ResponseEntity<EventDTO> createEvent(@PathVariable EventCreateDTO dto){
        EventDTO event = eventService.createEvent(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(uri).body(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable("id") Long id, @RequestBody EventUpdateDTO updateEventDto){
        EventDTO eventDto = eventService.updateEvent(id, updateEventDto);
        return ResponseEntity.ok().body(eventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id){
        eventService.removeEvent(id);
        return ResponseEntity.noContent().build();
    }

}
