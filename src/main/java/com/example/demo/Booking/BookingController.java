package com.example.demo.Booking;

import com.example.demo.customer.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Booking")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // book a flight API
    @PostMapping("/bookFlight")
    public void bookFlight(
            @RequestBody Booking booking
    ) {
        bookingService.bookFlight(booking);
    }

    // upgradeSeat
    @RequestMapping(value = "/upgradeSeat/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Optional<Booking> upgradeSeat(@PathVariable Long id) {
        return bookingService.upgradeSeat(id);
    }

    // downgradeSeat
    @RequestMapping(value = "/downgradeSeat/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Optional<Booking> downgradeSeat(@PathVariable Long id) {
        return bookingService.downgradeSeat(id);
    }

    //reading
    @GetMapping("/readBooking/{id}")
    @ResponseBody
    public Optional<Booking> getBooking(@PathVariable Long id) {
        return bookingService.getBooking(id);
    }

    @GetMapping("/readAllBookings")
    @ResponseBody
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/readAllCustomerBookings/{id}")
    @ResponseBody
    public List<Booking> getAllCustomerBookings(@PathVariable Long id) {
        return bookingService.getAllCustomerBookings(id);
    }
}
