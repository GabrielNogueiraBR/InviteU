package br.application.inviteu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.application.inviteu.dto.subEvent.SubEventDTO;
import br.application.inviteu.services.SubEventService;

@RestController
@RequestMapping("/api/subevents")
public class SubEventController {

    @Autowired
    private SubEventService subEventService;

    @GetMapping("/all")
    public ResponseEntity<List<SubEventDTO>> getSubEvents() {
        List<SubEventDTO> listSubEvents = subEventService.getAllSubEvents();
        return ResponseEntity.ok(listSubEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubEventDTO> getSubEventById(@PathVariable("id") Long id) {
        SubEventDTO subEventDto = subEventService.getSubEventById(id);
        return ResponseEntity.ok(subEventDto);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> removeSubEvent(@PathVariable("id") Long id) {
        subEventService.removeSubEvent(id);
        return ResponseEntity.noContent().build();
    }
}
