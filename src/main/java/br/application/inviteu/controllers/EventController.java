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
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> listEvents = eventService.getAllEvents();
        return ResponseEntity.ok(listEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventsById(@PathVariable("id") Long id) {
        EventDTO eventDto = eventService.getEventsById(id);
        return ResponseEntity.ok(eventDto);
    }

    @PostMapping("/new")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventCreateDTO createDto) {
        EventDTO eventDto = eventService.createEvent(createDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDto.getId()).toUri();
        return ResponseEntity.created(uri).body(eventDto);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable("id") Long id, @RequestBody EventUpdateDTO updateEventDto) {
        EventDTO eventDto = eventService.updateEvent(id, updateEventDto);
        return ResponseEntity.ok().body(eventDto);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> removeEvent(@PathVariable("id") Long id) {
        eventService.removeEvent(id);
        return ResponseEntity.noContent().build();
    }

}
