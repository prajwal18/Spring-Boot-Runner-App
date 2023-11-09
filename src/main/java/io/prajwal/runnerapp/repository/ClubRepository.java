package io.prajwal.runnerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.prajwal.runnerapp.model.Club;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
	Club findByTitle(String title);

	@Query("SELECT c FROM Club c WHERE c.title LIKE CONCAT('%',:query,'%')")
	List<Club> filterClubsByTitle(String query);
}
