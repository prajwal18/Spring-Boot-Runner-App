package io.prajwal.runnerapp.dto;


import io.prajwal.runnerapp.model.Club;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long id;
    @NotEmpty(message = "Event needs to have a name.")
    private String name;

    @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    @NotEmpty(message = "Event needs to have a Type.")
    private String type;
    @NotEmpty(message = "Event needs to have a PhotoURL.")
    private String photoUrl;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private Club club;
}
