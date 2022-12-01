package com.example.demo.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    // book a flight
    public Booking bookFlight(Booking booking) {
        bookingRepository.save(booking);
        return booking;
    }

    // upgrade seat
    public Optional<Booking> upgradeSeat(Long id) {
        bookingRepository.changeSeatType(SeatType.BUSINESS, id);
        return bookingRepository.findById(id);
    }

    // downgrade seat
    public Optional<Booking> downgradeSeat(Long id) {
        bookingRepository.changeSeatType(SeatType.BUSINESS, id);
        return bookingRepository.findById(id);
    }

    // readings
    public Optional<Booking> getBooking(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBookings(Long id) {
        return bookingRepository.findAll();
    }

    public List<Booking> getAllCustomerBookings(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }
}
