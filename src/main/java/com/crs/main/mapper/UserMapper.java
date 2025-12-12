package com.crs.main.mapper;

import com.crs.main.dto.UserDTO;
import com.crs.main.model.User;
import com.crs.main.security.UserRegisterRequest;

import java.time.Instant;
import java.time.LocalDate;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setDriverLicenseNumber(user.getDriverLicenseNumber());
        dto.setProfilePictureUrl(user.getProfilePictureUrl());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setState(user.getState());
        dto.setZipCode(user.getZipCode());
        dto.setCountry(user.getCountry());
        dto.setAccountStatus(user.getAccountStatus());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setDriverLicenseNumber(dto.getDriverLicenseNumber());
        user.setProfilePictureUrl(dto.getProfilePictureUrl());
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setState(dto.getState());
        user.setZipCode(dto.getZipCode());
        user.setCountry(dto.getCountry());
        user.setAccountStatus(dto.getAccountStatus());
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return user;
    }

    public static User registerToEntity(UserRegisterRequest dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setName(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setDriverLicenseNumber(dto.getDriverLicenseNumber());
        user.setProfilePictureUrl(dto.getProfilePictureUrl());
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setState(dto.getState());
        user.setZipCode(dto.getZipCode());
        user.setCountry(dto.getCountry());
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return user;
    }
}
