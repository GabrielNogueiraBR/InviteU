package br.application.inviteu.services;

import br.application.inviteu.dto.event.EventCreateDTO;
import br.application.inviteu.dto.event.EventDTO;
import br.application.inviteu.dto.event.EventUpdateDTO;
import br.application.inviteu.entities.Event;
import br.application.inviteu.entities.SubEvent;
import br.application.inviteu.entities.User;
import br.application.inviteu.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventDTO> getAllEvents() {
        List<Event> listEvents = eventRepository.findAll();

        if (listEvents.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No events to be shown.");
        }
        return toListDTO(listEvents);
    }

    public EventDTO getEventsById(Long id) {
        Optional<Event> opEvent = eventRepository.findById(id);

        Event event = opEvent.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No events with this Id to shown."));

        return new EventDTO(event);
    }

    public EventDTO createEvent(EventCreateDTO newEventDTO, User user) {
        Event eventEntity = new Event(newEventDTO);
        eventEntity.setOwner(user);
        
        try {
            eventEntity = eventRepository.save(eventEntity);
            return new EventDTO(eventEntity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error when saving the event on the database");
        }
    }

    @Transactional
    public EventDTO updateEvent(Long id, EventUpdateDTO eventUpdateDto, Long idUser) {
        try {
            Event event = eventRepository.getOne(id);

            if(event.getOwner().getId() == idUser){
                event.setTitle(eventUpdateDto.getTitle());
                event.setDescription(eventUpdateDto.getDescription());
                event.setIsPublic(eventUpdateDto.getIsPublic());
                event.setAddress(eventUpdateDto.getAddress());
    
                event = eventRepository.save(event);
    
                return new EventDTO(event);
            }
            else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to edit this object.");
            }

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No events with this Id to be shown.");
        }
    }

    public void removeEvent(Long idEvent, Long idUser) {
        try {
            
            Event event = eventRepository.getOne(idEvent);

            if(event.getOwner().getId() == idUser){
                eventRepository.deleteById(idEvent);
            }
            else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this object.");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No events with this Id to be shown.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event could not be deleted");
        }
    }

    private List<EventDTO> toListDTO(List<Event> events) {
        List<EventDTO> listDTO = new ArrayList<>();

        for (Event event : events) {
            listDTO.add(new EventDTO(event));
        }
        return listDTO;
    }

    private Boolean verifyDateAndTime(List<SubEvent> listSubEvents) {
        for (SubEvent subEvent : listSubEvents) {
            if (subEvent.getEndDateTime().isAfter(subEvent.getStartDateTime())) {
                return true;
            }
        }
        return false;
    }

}
