package io.prajwal.runnerapp.dto;

import io.prajwal.runnerapp.model.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTO {
    private Long id;
    @NotEmpty(message = "The title cannot be empty.")
    private String title;
    @NotEmpty(message = "The photoUrl cannot be empty.")
    private String photoUrl;
    @NotEmpty(message = "The content cannot be empty.")
    private String content;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private List<EventDTO> events;
    private UserEntity createdBy;
}
