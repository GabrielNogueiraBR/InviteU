package br.application.inviteu.controllers;

import br.application.inviteu.dto.event.EventCreateDTO;
import br.application.inviteu.dto.event.EventDTO;
import br.application.inviteu.dto.event.EventUpdateDTO;
import br.application.inviteu.dto.subEvent.SubEventCreateDTO;
import br.application.inviteu.dto.subEvent.SubEventDTO;
import br.application.inviteu.dto.subEvent.SubEventUpdateDTO;
import br.application.inviteu.entities.Event;
import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.services.EventService;
import br.application.inviteu.services.SubEventService;
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

    @Autowired
    private SubEventService subEventService;

    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getEvents() {
        List<EventDTO> listEvents = eventService.getAllEvents();
        return ResponseEntity.ok(listEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventsById(@PathVariable("id") Long id) {
        EventDTO eventDto = eventService.getEventsById(id);
        return ResponseEntity.ok(eventDto);
    }

    // @PostMapping("/new")
    // public ResponseEntity<EventDTO> createEvent(@RequestBody EventCreateDTO createDto) {
    //     EventDTO eventDto = eventService.createEvent(createDto);
    //     URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDto.getId()).toUri();
    //     return ResponseEntity.created(uri).body(eventDto);
    // }

    @PostMapping("/{eventId}/subEvent/new")
    public ResponseEntity<SubEventDTO> createSubEvent(@PathVariable("eventId") Long eventId, @RequestBody SubEventCreateDTO createDto) {
        Event event = new Event(eventService.getEventsById(eventId));

        SubEventDTO subEventDto = subEventService.createSubEvent(event, createDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subEventDto.getId()).toUri(); // ARRUMAR URI
        return ResponseEntity.created(uri).body(subEventDto);
    }

    // @PutMapping("/{id}/edit")
    // public ResponseEntity<EventDTO> updateEvent(@PathVariable("id") Long id, @RequestBody EventUpdateDTO updateEventDto) {
    //     EventDTO eventDto = eventService.updateEvent(id, updateEventDto);
    //     return ResponseEntity.ok().body(eventDto);
    // }

    @PutMapping("/{eventId}/subEvent/{subId}/edit")
    public ResponseEntity<SubEventDTO> updateSubEvent(@PathVariable("eventId") Long eventId, @PathVariable("subId") Long subId, @RequestBody SubEventUpdateDTO updateSubEventDto) {
        SubEventDTO subEventDto = subEventService.updateSubEvent(subId, updateSubEventDto);
        return ResponseEntity.ok().body(subEventDto);
    }

    // @DeleteMapping("/{id}/delete")
    // public ResponseEntity<Void> removeEvent(@PathVariable("id") Long id) {
    //     eventService.removeEvent(id);
    //     return ResponseEntity.noContent().build();
    // }

}
