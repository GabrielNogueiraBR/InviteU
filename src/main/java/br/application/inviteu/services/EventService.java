package br.application.inviteu.services;

import br.application.inviteu.dtos.Event.EventDTO;
import br.application.inviteu.entities.Event;
import br.application.inviteu.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    private List<EventDTO> toListDTO(List<Event> events) {
        List<EventDTO> listDTO = new ArrayList<>();
        for (Event event : events) {
            listDTO.add(toDTO(event));
        }
        return listDTO;
    }

    private EventDTO toDTO(Event event) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setIsPublic(event.getIsPublic());
        eventDTO.setOwner(event.getOwner());
        eventDTO.setSubEvent(event.getSubEvents());
        eventDTO.setAddress(event.getAddress());

        return eventDTO;
    }
}
