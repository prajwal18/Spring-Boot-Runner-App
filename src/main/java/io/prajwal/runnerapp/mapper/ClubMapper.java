package io.prajwal.runnerapp.mapper;

import io.prajwal.runnerapp.dto.ClubDTO;
import io.prajwal.runnerapp.model.Club;

import java.util.stream.Collectors;

import static io.prajwal.runnerapp.mapper.EventMapper.mapToEventDTO;

public class ClubMapper {
    public static Club mapToClub(ClubDTO clubDto) {
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .photoUrl(clubDto.getPhotoUrl())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .createdBy(clubDto.getCreatedBy())
                .build();
        return club;
    }

    public static ClubDTO mapToClubDTO(Club club) {
        ClubDTO clubDTO = ClubDTO.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photoUrl(club.getPhotoUrl())
                .events(club.getEvents().stream().map(EventMapper::mapToEventDTO).collect(Collectors.toList()))
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .createdBy(club.getCreatedBy())
                .build();
        return clubDTO;
    }
}
