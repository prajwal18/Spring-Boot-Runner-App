package io.prajwal.runnerapp.service.impl;

import io.prajwal.runnerapp.dto.RegistrationDTO;
import io.prajwal.runnerapp.model.Role;
import io.prajwal.runnerapp.model.UserEntity;
import io.prajwal.runnerapp.repository.RoleRepository;
import io.prajwal.runnerapp.repository.UserRepository;
import io.prajwal.runnerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveUser(RegistrationDTO registrationUser) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationUser.getUsername());
        user.setEmail(registrationUser.getEmail());
        user.setPassword(encoder.encode(registrationUser.getPassword()));

        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
