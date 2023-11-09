package io.prajwal.runnerapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private Long id;

    @NotEmpty(message = "The username cannot be empty.")
    private String username;

    @NotEmpty(message = "The email cannot be empty.")
    private String email;

    @NotEmpty(message = "The password cannot be empty.")
    private String password;
}
