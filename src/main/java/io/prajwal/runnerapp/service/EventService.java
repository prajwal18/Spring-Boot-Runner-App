package io.prajwal.runnerapp.service;

import io.prajwal.runnerapp.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getAllEvents();

    List<EventDTO> filterEventsByName(String query);

    void saveEvent(long clubId, EventDTO eventDto);

    EventDTO findEventById(long id);

    void updateEvent(EventDTO eventDto);

    void deleteEventById(long id);

}
