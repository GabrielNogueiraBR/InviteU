package br.application.inviteu.services;

import br.application.inviteu.dto.event.EventCreateDTO;
import br.application.inviteu.dto.event.EventDTO;
import br.application.inviteu.dto.event.EventUpdateDTO;
import br.application.inviteu.entities.Event;
import br.application.inviteu.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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

    public Event getEventsById(Long id){
        Optional<Event> op = eventRepository.findById(id);
        return op.orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT, "No events to be shown."));
    }

    public EventDTO saveEvent(EventCreateDTO eventDTO){
        Event event = eventRepository.save(this.fromDTO(eventDTO));
        return this.toDTO(event);
    }

    public Event updateEvent(Long id, EventUpdateDTO eventDTO){
        try{
            Event event = getEventsById(id);
            event.setTitle(eventDTO.getTitle());
            event.setDescription(eventDTO.getDescription());
            event.setIsPublic(eventDTO.getIsPublic());
            event.setAddress(eventDTO.getAddress());
            return eventRepository.save(event);
        }
        catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No events to be shown.");
        }
    }

    public void deleteEvent(Long id){
        try{
            eventRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK, "The event has been deleted");
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No events to be shown.");
        }
    }

    private List<EventDTO> toListDTO(List<Event> events) {
        List<EventDTO> listDTO = new ArrayList<>();

        for (Event event : events) {
            listDTO.add(toDTO(event));
        }
        return listDTO;
    }

    private EventDTO toDTO(Event event) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setIsPublic(event.getIsPublic());
        eventDTO.setOwner(event.getOwner());
        eventDTO.setSubEvent(event.getSubEvents());
        eventDTO.setAddress(event.getAddress());

        return eventDTO;
    }

    private Event fromDTO(EventCreateDTO dto){
        Event event = new Event();

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setIsPublic(dto.getIsPublic());
        event.setOwner(dto.getOwner());
        event.setSubEvents(dto.getSubEvent());
        event.setAddress(dto.getAddress());

        return event;
    }
}
