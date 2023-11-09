package io.prajwal.runnerapp.repository;

import io.prajwal.runnerapp.model.Club;
import io.prajwal.runnerapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Club findByName(String name);

    @Query("SELECT e FROM Event e WHERE e.name LIKE CONCAT('%',:query,'%')")
    List<Event> filterEventsByName(String query);
}
