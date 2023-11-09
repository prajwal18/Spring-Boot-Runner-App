package io.prajwal.runnerapp.mapper;

import io.prajwal.runnerapp.dto.EventDTO;
import io.prajwal.runnerapp.model.Event;

public class EventMapper {

    public static Event mapToEvent(EventDTO eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .club(eventDto.getClub())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();
        return event;
    }

    public static EventDTO mapToEventDTO(Event event) {
        EventDTO eventDto = EventDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .club(event.getClub())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .build();
        return eventDto;
    }
}
