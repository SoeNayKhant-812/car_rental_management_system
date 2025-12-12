package com.crs.main.security;

import com.crs.main.util.enums.UserUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String username;
    private String password;
    private String email;
    private UserUtils.UserRole role;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String driverLicenseNumber;
    private String profilePictureUrl;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
