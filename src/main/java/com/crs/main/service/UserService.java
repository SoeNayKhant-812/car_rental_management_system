package com.crs.main.service;

import com.crs.main.dto.UserDTO;
import com.crs.main.mapper.UserMapper;
import com.crs.main.model.User;
import com.crs.main.repository.UserRepository;
import com.crs.main.security.UserRegisterRequest;
import com.crs.main.util.enums.UserUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initDefaultUsers() {
        if (userRepository.findAll().isEmpty()) {
            UserRegisterRequest adminRequest = new UserRegisterRequest();
            adminRequest.setUsername("admin");
            adminRequest.setPassword("adminpass");
            adminRequest.setEmail("admin@example.com");
            adminRequest.setRole(UserUtils.UserRole.ADMIN);

            UserRegisterRequest userRequest = new UserRegisterRequest();
            userRequest.setUsername("user");
            userRequest.setPassword("userpass");
            userRequest.setEmail("user@example.com");
            userRequest.setRole(UserUtils.UserRole.USER);

            try {
                UserDTO defaultAdmin = createUser(adminRequest);
                UserDTO defaultUser = createUser(userRequest);
                logger.info("Default admin" + defaultAdmin.getName() + defaultAdmin.getEmail() + " account created initially!");
                logger.info("Default user" + defaultUser.getName() + defaultUser.getEmail() + " account created initially!");
            } catch (Exception ex) {
                logger.error("Failed to create default users: {}", ex.getMessage(), ex);
            }
        }
    }

    @PreDestroy
    public void invalidateAllUserTokensOnShutdown() {
        try {
            List<User> allUsers = userRepository.findAll();
            Instant shutdownTime = Instant.now();

            for (User user : allUsers) {
                user.setLastLogOutAt(shutdownTime);
                userRepository.save(user);
            }
            logger.info("Successfully invalidated tokens for {} users.", allUsers.size());
        } catch (Exception e) {
            logger.error("Error during token invalidation on shutdown.", e);
        }
    }

    private User createUserObject(UserRegisterRequest request) {
        User user = new User();
        user.setName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        return user;
    }

    private UserDTO createUser(UserRegisterRequest request) {
        User newUser = createUserObject(request);
        return UserMapper.toDTO(userRepository.save(newUser));
    }


    public UserDTO save(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        user.setCreatedAt(Instant.now());
        return UserMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }

    public UserDTO findById(long id) {
        return UserMapper.toDTO(userRepository.findById(id).orElse(null));
    }

    public Optional<UserDTO> findByEmail(String email) {
        return Optional.ofNullable(UserMapper.toDTO(userRepository.findByEmail(email).orElseThrow()));
    }

    public Optional<UserDTO> findByName(String username) {
        return Optional.ofNullable(UserMapper.toDTO(userRepository.findByName(username).orElseThrow()));
    }

    public UserDTO update(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        User updatedUser = new User();
        if (existingUser != null) {
            User user = UserMapper.toEntity(userDto);
            user.setId(id);
            user.setUpdatedAt(Instant.now());
            updatedUser = userRepository.save(user);
        }
        return UserMapper.toDTO(updatedUser);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
