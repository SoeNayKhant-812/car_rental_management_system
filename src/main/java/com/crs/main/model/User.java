package com.crs.main.model;

import com.crs.main.util.enums.UserUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String password;
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

    private Instant createdAt;
    private Instant updatedAt;
    private Instant lastLogOutAt;
}
