package br.application.inviteu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.application.inviteu.dto.subEvent.SubEventCreateDTO;
import br.application.inviteu.dto.subEvent.SubEventDTO;
import br.application.inviteu.dto.subEvent.SubEventUpdateDTO;
import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.repositories.SubEventRepository;

@Service
public class SubEventService {

    @Autowired
    private SubEventRepository subEventRepository;

    public List<SubEventDTO> getAllSubEvents(){
        List<SubEvent> listSubEvents = subEventRepository.findAll();

        if(listSubEvents.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "");
        }
        return toListDTO(listSubEvents);
    }

    public SubEvent getSubEventById(Long id){
        Optional<SubEvent> op = subEventRepository.findById(id);
        return op.orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT, "No events to be shown."));

    }

    public SubEventDTO saveEvent(SubEventCreateDTO subEventDTO){
        SubEvent event = subEventRepository.save(this.fromDTO(subEventDTO));
        return this.toDTO(event);
    }

    public SubEvent updateSubEvent(Long id, SubEventUpdateDTO subEventDTO){
        try{
            SubEvent subEvent = getSubEventById(id);

            subEvent.setTitle(subEventDTO.getTitle());
            subEvent.setDescription(subEventDTO.getDescription());
            subEvent.setStartDateTime(subEventDTO.getStartDateTime());
            subEvent.setEndDateTime(subEventDTO.getEndDateTime());
            subEvent.setIsLimited(subEventDTO.getIsLimited());
            subEvent.setCapacity(subEventDTO.getCapacity());
            subEvent.setStatus(subEventDTO.getStatus());

            return subEventRepository.save(subEvent);
        }
        catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No events to be shown.");
        }
    }

    public void deleteSubEvent(Long id){
        try{
            subEventRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK, "The event has been deleted");
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No events to be shown.");
        }
    }
    
    private List<SubEventDTO> toListDTO(List<SubEvent> events) {
        List<SubEventDTO> listDTO = new ArrayList<>();

        for (SubEvent event : events) {
            listDTO.add(toDTO(event));
        }
        return listDTO;
    }

    private SubEventDTO toDTO(SubEvent subEvent) {
        SubEventDTO subEventDTO = new SubEventDTO();

        subEventDTO.setId(subEvent.getId());
        subEventDTO.setTitle(subEvent.getTitle());
        subEventDTO.setDescription(subEvent.getDescription());
        subEventDTO.setStartDateTime(subEvent.getStartDateTime());
        subEventDTO.setEndDateTime(subEvent.getEndDateTime());
        subEventDTO.setIsLimited(subEvent.getIsLimited());
        subEventDTO.setCapacity(subEvent.getCapacity());
        subEventDTO.setStatus(subEvent.getStatus());

        return subEventDTO;
    }

    private SubEvent fromDTO(SubEventCreateDTO subEventDTO){
        SubEvent subEvent = new SubEvent();

        subEvent.setTitle(subEventDTO.getTitle());
        subEvent.setDescription(subEventDTO.getDescription());
        subEvent.setStartDateTime(subEventDTO.getStartDateTime());
        subEvent.setEndDateTime(subEventDTO.getEndDateTime());
        subEvent.setIsLimited(subEventDTO.getIsLimited());
        subEvent.setCapacity(subEventDTO.getCapacity());
        subEvent.setStatus(subEventDTO.getStatus());

        return subEvent;
    }

}
