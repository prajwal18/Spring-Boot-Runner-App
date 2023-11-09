package io.prajwal.runnerapp.service;

import java.util.List;

import io.prajwal.runnerapp.dto.ClubDTO;

public interface ClubService {
	List<ClubDTO> getAllClubs();

	void saveClub(ClubDTO clubDto);

	ClubDTO findClubById(long id);

	void updateClub(ClubDTO clubDto);

	void deleteClubById(long clubId);

	List<ClubDTO> filterClubsByTitle(String query);
}
