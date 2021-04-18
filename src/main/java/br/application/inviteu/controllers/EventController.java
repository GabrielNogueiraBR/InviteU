package br.application.inviteu.controllers;

import br.application.inviteu.dtos.Event.EventDTO;
import br.application.inviteu.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping()
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> listEvents = eventService.getAllEvents();
        return ResponseEntity.ok(listEvents);
    }

}
