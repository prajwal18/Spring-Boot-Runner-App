package io.prajwal.runnerapp.service;

import io.prajwal.runnerapp.dto.RegistrationDTO;
import io.prajwal.runnerapp.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDTO registrationUser);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);
}
