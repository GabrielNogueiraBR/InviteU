package br.application.inviteu.services;

import br.application.inviteu.dto.subEvent.SubEventCreateDTO;
import br.application.inviteu.dto.subEvent.SubEventDTO;
import br.application.inviteu.dto.subEvent.SubEventUpdateDTO;
import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.repositories.SubEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubEventService {

    @Autowired
    private SubEventRepository subEventRepository;

    public List<SubEventDTO> getAllSubEvents(){
        List<SubEvent> listSubEvents = subEventRepository.findAll();

        if(listSubEvents.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No subEvents to be shown.");
        }
        return toListDTO(listSubEvents);
    }

    public SubEventDTO getSubEventById(Long id){
        Optional<SubEvent> opSubEvent = subEventRepository.findById(id);

        SubEvent subEvent = opSubEvent.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subEvents with this Id to shown."));

        return new SubEventDTO(subEvent);
    }

    public SubEventDTO createSubEvent(SubEventCreateDTO newSubEventDto){
        if (verifyDateAndTime(newSubEventDto)) {
            try {
                SubEvent subEvent = subEventRepository.save(new SubEvent(newSubEventDto));
                return new SubEventDTO(subEvent);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error when saving the subEvent on the database");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must enter a start date that is earlier than the end.");
        }
    }

    public SubEventDTO updateSubEvent(Long id, SubEventUpdateDTO subEventUpdateDto){
        try{
            SubEvent subEvent = subEventRepository.getOne(id);

            subEvent.setTitle(subEventUpdateDto.getTitle());
            subEvent.setDescription(subEventUpdateDto.getDescription());
            subEvent.setStartDateTime(subEventUpdateDto.getStartDateTime());
            subEvent.setEndDateTime(subEventUpdateDto.getEndDateTime());
            subEvent.setIsLimited(subEventUpdateDto.getIsLimited());
            subEvent.setCapacity(subEventUpdateDto.getCapacity());
            subEvent.setStatus(subEventUpdateDto.getStatus());

            subEvent = subEventRepository.save(subEvent);

            return new SubEventDTO(subEvent);
        }
        catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No subEvents with this Id to shown.");
        }
    }

    public void removeSubEvent(Long id){
        try{
            subEventRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK, "The subEvent has been deleted");
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No subEvents with this Id to shown.");
        }
    }
    
    private List<SubEventDTO> toListDTO(List<SubEvent> subEvents) {
        List<SubEventDTO> listDTO = new ArrayList<>();

        for (SubEvent subEvent : subEvents) {
            listDTO.add(new SubEventDTO(subEvent));
        }
        return listDTO;
    }

    private Boolean verifyDateAndTime(SubEventCreateDTO subEventDto) {
        return subEventDto.getEndDateTime().isAfter(subEventDto.getStartDateTime());
    }

}
