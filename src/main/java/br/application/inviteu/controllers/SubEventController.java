package br.application.inviteu.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.application.inviteu.dto.subEvent.SubEventCreateDTO;
import br.application.inviteu.dto.subEvent.SubEventDTO;
import br.application.inviteu.dto.subEvent.SubEventUpdateDTO;
import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.services.SubEventService;

@RestController
@RequestMapping("/api/subevent")
public class SubEventController {
    
    @Autowired
    private SubEventService subEventService;

    @GetMapping
    public ResponseEntity<List<SubEventDTO>> getAllSubEvents() {
        List<SubEventDTO> listSubEvents = subEventService.getAllSubEvents();
        return ResponseEntity.ok(listSubEvents);
    }

    @GetMapping("{id}")
    public ResponseEntity<SubEvent> getSubEventsById(@PathVariable Long id){
        SubEvent subEvent = subEventService.getSubEventById(id);
        return ResponseEntity.ok(subEvent);
    }

    @PostMapping
    public ResponseEntity<SubEventDTO> saveSubEvent(@RequestBody SubEventCreateDTO dto){
        SubEventDTO event = subEventService.saveEvent(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(uri).body(event);
    }

    @PutMapping("{id}")
    public ResponseEntity<SubEvent> updateSubEvent(@PathVariable Long id, @RequestBody SubEventUpdateDTO dto){
        SubEvent event = subEventService.updateSubEvent(id, dto);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSubEvent(@PathVariable Long id){
        subEventService.deleteSubEvent(id);
        return ResponseEntity.noContent().build();
    }
}

