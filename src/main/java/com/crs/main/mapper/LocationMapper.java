package com.crs.main.mapper;

import com.crs.main.dto.LocationDTO;
import com.crs.main.model.Location;

public class LocationMapper {
    public static LocationDTO toDTO(Location location) {
        if (location == null) {
            return null;
        }
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setAddress(location.getAddress());
        locationDTO.setCity(location.getCity());
        locationDTO.setState(location.getState());
        locationDTO.setZip_code(location.getZip_code());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setPhone_number(location.getPhone_number());
        return locationDTO;
    }

    public static Location toEntity(LocationDTO dto) {
        if (dto == null) {
            return null;
        }
        Location location = new Location();
        location.setId(dto.getId());
        location.setName(dto.getName());
        location.setAddress(dto.getAddress());
        location.setCity(dto.getCity());
        location.setState(dto.getState());
        location.setZip_code(dto.getZip_code());
        location.setCountry(dto.getCountry());
        location.setPhone_number(dto.getPhone_number());
        return location;
    }
}
