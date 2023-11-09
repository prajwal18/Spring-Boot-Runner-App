package io.prajwal.runnerapp.service.impl;

import io.prajwal.runnerapp.dto.ClubDTO;
import io.prajwal.runnerapp.mapper.ClubMapper;
import io.prajwal.runnerapp.model.Club;
import io.prajwal.runnerapp.model.UserEntity;
import io.prajwal.runnerapp.repository.ClubRepository;
import io.prajwal.runnerapp.repository.UserRepository;
import io.prajwal.runnerapp.service.ClubService;
import io.prajwal.runnerapp.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.prajwal.runnerapp.mapper.ClubMapper.mapToClub;
import static io.prajwal.runnerapp.mapper.ClubMapper.mapToClubDTO;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<ClubDTO> getAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(ClubMapper::mapToClubDTO).collect(Collectors.toList());
    }

    @Override
    public void saveClub(ClubDTO clubDto) {
        Club club = mapToClub(clubDto);
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findFirstByUsername(username);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public ClubDTO findClubById(long id) {
        Club club = clubRepository.findById(id).get();
        return mapToClubDTO(club);
    }

    @Override
    public void updateClub(ClubDTO clubDto) {
        Club club = mapToClub(clubDto);
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findFirstByUsername(username);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void deleteClubById(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDTO> filterClubsByTitle(String query) {
        List<Club> clubs = clubRepository.filterClubsByTitle(query);
        List<ClubDTO> clubDTOs = clubs.stream().map(ClubMapper::mapToClubDTO).collect(Collectors.toList());
        return  clubDTOs;
    }

}
