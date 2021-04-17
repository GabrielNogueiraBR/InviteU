package br.application.inviteu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.application.inviteu.entities.Status;
import br.application.inviteu.repositories.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository repository;

    public List<Status> getAllStatus() {
        return repository.findAll();
    }
    
}
