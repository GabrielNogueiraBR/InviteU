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

    @GetMapping("/all")
    public ResponseEntity<List<SubEventDTO>> getAllSubEvents() {
        List<SubEventDTO> listSubEvents = subEventService.getAllSubEvents();
        return ResponseEntity.ok(listSubEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubEventDTO> getSubEventsById(@PathVariable("id") Long id){
        SubEventDTO subEventDto = subEventService.getSubEventById(id);
        return ResponseEntity.ok(subEventDto);
    }

    @PostMapping("/new")
    public ResponseEntity<SubEventDTO> saveSubEvent(@RequestBody SubEventCreateDTO createDto){
        SubEventDTO subEventDto = subEventService.createSubEvent(createDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subEventDto.getId()).toUri();
        return ResponseEntity.created(uri).body(subEventDto);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<SubEventDTO> updateSubEvent(@PathVariable("id") Long id, @RequestBody SubEventUpdateDTO subEventUpdateDto){
        SubEventDTO subEventDto = subEventService.updateSubEvent(id, subEventUpdateDto);
        return ResponseEntity.ok(subEventDto);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> removeSubEvent(@PathVariable("id") Long id){
        subEventService.removeSubEvent(id);
        return ResponseEntity.noContent().build();
    }
}

