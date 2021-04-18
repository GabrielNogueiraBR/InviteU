package br.application.inviteu.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.application.inviteu.dto.StatusInsertDTO;
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

    public Status saveStatus(Status status) {
        return repository.save(status);
    }

    public Status updateStatus(Long id, StatusInsertDTO dto) {
        try{
            Status status = getStatusById(id);
            status.setDescription(dto.getDescription());
            return repository.save(status);
        }
        catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found");
        }
    }

    public void deleteStatus(Long id) {
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found");
        }
        catch(DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status could not be deleted");
        }
    }
    
}
