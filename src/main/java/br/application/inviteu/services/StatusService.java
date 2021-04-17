package br.application.inviteu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.application.inviteu.entities.Status;
import br.application.inviteu.repositories.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository repository;

    public List<Status> getAllStatus() {
        return repository.findAll();
    }

    public Status getStatusById(Long id) {
        Optional<Status> op = repository.findById(id);
        return op.orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found"));    
    }
    
}
