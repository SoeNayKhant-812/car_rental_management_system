package com.crs.main.service;

import com.crs.main.dto.UserDTO;
import com.crs.main.mapper.UserMapper;
import com.crs.main.model.User;
import com.crs.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO save(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        return UserMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }

    public UserDTO findById(long id) {
        return UserMapper.toDTO(userRepository.findById(id).orElse(null));
    }

    public UserDTO findByEmail(String email) {
        return UserMapper.toDTO(userRepository.findByEmail(email));
    }

    public UserDTO findByName(String username) {
        return UserMapper.toDTO(userRepository.findByName(username));
    }

    public UserDTO update(Long id,UserDTO userDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        User updatedUser = new User();
        if (existingUser != null) {
            User user = UserMapper.toEntity(userDto);
            user.setId(id);
            updatedUser = userRepository.save(user);
        }
        return UserMapper.toDTO(updatedUser);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
