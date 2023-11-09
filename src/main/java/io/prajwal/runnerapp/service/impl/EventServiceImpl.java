package io.prajwal.runnerapp.service.impl;

import io.prajwal.runnerapp.dto.EventDTO;
import io.prajwal.runnerapp.mapper.EventMapper;
import io.prajwal.runnerapp.model.Club;
import io.prajwal.runnerapp.model.Event;
import io.prajwal.runnerapp.repository.ClubRepository;
import io.prajwal.runnerapp.repository.EventRepository;
import io.prajwal.runnerapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.prajwal.runnerapp.mapper.EventMapper.mapToEvent;
import static io.prajwal.runnerapp.mapper.EventMapper.mapToEventDTO;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ClubRepository clubRepository;

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::mapToEventDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> filterEventsByName(String query) {
        return eventRepository
                .filterEventsByName(query).stream()
                .map(EventMapper::mapToEventDTO).collect(Collectors.toList());
    }

    @Override
    public void saveEvent(long clubId, EventDTO eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public EventDTO findEventById(long id) {
        Event event = eventRepository.findById(id).get();
        return mapToEventDTO(event);
    }

    @Override
    public void updateEvent(EventDTO eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEventById(long id) {
        eventRepository.deleteById(id);
    }

}