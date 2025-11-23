package com.crs.main.service;

import com.crs.main.dto.RentDTO;
import com.crs.main.mapper.RentMapper;
import com.crs.main.model.Rent;
import com.crs.main.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    private final RentRepository rentRepository;

    @Autowired
    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public RentDTO save(RentDTO bookingDTO) {
        Rent booking = RentMapper.toEntity(bookingDTO);
        return RentMapper.toDTO(rentRepository.save(booking));
    }

    public List<RentDTO> findAll() {
        return rentRepository.findAll().stream().map(RentMapper::toDTO).toList();
    }

    public RentDTO findById(long id) {
        return RentMapper.toDTO(rentRepository.findById(id).orElse(null));
    }

    public RentDTO update(Long id, RentDTO bookingDto) {
        Rent existingBooking = rentRepository.findById(id).orElse(null);
        Rent updatedBooking = new Rent();
        if (existingBooking != null) {
            Rent booking = RentMapper.toEntity(bookingDto);
            booking.setId(id);
            updatedBooking = rentRepository.save(booking);
        }
        return RentMapper.toDTO(updatedBooking);
    }

    public void deleteById(long id) {
        rentRepository.deleteById(id);
    }
}
