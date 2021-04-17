package br.application.inviteu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
