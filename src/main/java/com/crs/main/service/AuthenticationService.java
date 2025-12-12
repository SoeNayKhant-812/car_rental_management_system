package com.crs.main.service;

import com.crs.main.dto.UserDTO;
import com.crs.main.mapper.UserMapper;
import com.crs.main.model.User;
import com.crs.main.repository.UserRepository;
import com.crs.main.security.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO registerUser(UserRegisterRequest registerRequest) {
        User user = UserMapper.registerToEntity(registerRequest);
        return UserMapper.toDTO(userRepository.save(user));
    }

    public void logout(String username) {

        User user = userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setLastLogOutAt(Instant.now());
        userRepository.save(user);
    }
}
