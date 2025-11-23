package com.crs.main.dto;

import com.crs.main.util.enums.UserUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private long id;

    private String name;
    private String email;
    private UserUtils.UserRole role;  // enum used
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String driverLicenseNumber;
    private String profilePictureUrl;

    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private UserUtils.AccountStatus accountStatus;   // enum used
}
