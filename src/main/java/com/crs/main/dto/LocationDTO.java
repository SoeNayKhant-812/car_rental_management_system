package com.crs.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private long id;

    private String name;
    private String address;
    private String city;
    private String state;
    private String zip_code;
    private String country;
    private String phone_number;
}
