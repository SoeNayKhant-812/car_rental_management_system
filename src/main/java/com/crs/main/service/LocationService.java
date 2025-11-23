package com.crs.main.service;

import com.crs.main.dto.LocationDTO;
import com.crs.main.mapper.LocationMapper;
import com.crs.main.model.Location;
import com.crs.main.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationDTO saveLocation(LocationDTO locationDto) {
        Location location = LocationMapper.toEntity(locationDto);
        return LocationMapper.toDTO(locationRepository.save(location));
    }

    public List<LocationDTO> getLocations() {
        return locationRepository.findAll().stream().map(LocationMapper::toDTO).toList();
    }

    public LocationDTO getLocationById(Long id) {
        return LocationMapper.toDTO(locationRepository.findById(id).orElse(null));
    }

    public LocationDTO update(Long id, LocationDTO locationDto) {
        Location existingLocation = locationRepository.findById(id).orElse(null);
        Location updatedLocation = new Location();
        if (existingLocation != null) {
            Location location = LocationMapper.toEntity(locationDto);
            location.setId(id);
            updatedLocation = locationRepository.save(location);
        }
        return LocationMapper.toDTO(updatedLocation);
    }

    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }
}
