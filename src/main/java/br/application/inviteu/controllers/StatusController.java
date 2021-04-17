package br.application.inviteu.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.application.inviteu.dto.StatusInsertDTO;
import br.application.inviteu.entities.Status;
import br.application.inviteu.services.StatusService;


@RestController
@RequestMapping("/status")
public class StatusController {
    
    @Autowired
    private StatusService service;

    @GetMapping()
    public ResponseEntity<List<Status>> getAllStatus(){
        List<Status> list = service.getAllStatus();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Long id) {
        Status status = service.getStatusById(id);
        return ResponseEntity.ok(status);
    }
    
    @PostMapping()
    public ResponseEntity<Status> saveStatus(@RequestBody StatusInsertDTO dto){
        Status status = service.saveStatus(dto.toStatus());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(status.getId()).toUri();
        return ResponseEntity.created(uri).body(status);
    }
}
