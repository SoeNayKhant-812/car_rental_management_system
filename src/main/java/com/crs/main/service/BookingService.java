package com.crs.main.service;

import com.crs.main.dto.BookingDTO;
import com.crs.main.mapper.BookingMapper;
import com.crs.main.model.Booking;
import com.crs.main.repository.BookingRepository;
import com.crs.main.util.enums.BookingUtils;
import com.crs.main.util.enums.PaymentUtils;
import com.crs.main.util.enums.RentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingDTO save(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.toEntity(bookingDTO);
        if(booking.getBooking_status() == null) {
            booking.setBooking_status(BookingUtils.BookingStatus.PENDING);
        }
        return BookingMapper.toDTO(bookingRepository.save(booking));
    }

    public List<BookingDTO> findAll() {
        return bookingRepository.findAll().stream().map(BookingMapper::toDTO).toList();
    }

    public BookingDTO findById(long id) {
        return BookingMapper.toDTO(bookingRepository.findById(id).orElse(null));
    }

    public BookingDTO update(Long id, BookingDTO bookingDto) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        Booking updatedBooking = new Booking();
        if (existingBooking != null) {
            Booking booking = BookingMapper.toEntity(bookingDto);
            booking.setId(id);
            updatedBooking = bookingRepository.save(booking);
        }
        return BookingMapper.toDTO(updatedBooking);
    }

    public void deleteById(long id) {
        bookingRepository.deleteById(id);
    }
}
