package com.example.demo.Booking;

import com.example.demo.APIResponses.APIResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> bookFlight(
            @RequestBody Booking booking
    ) {
        try{
            bookingService.bookFlight(booking);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Booked successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    // upgradeSeat
    @RequestMapping(value = "/upgradeSeat/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> upgradeSeat(@PathVariable Long id) {
        try{
            Optional<Booking> upgraded =  bookingService.upgradeSeat(id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Booking updated successfully"), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    // downgradeSeat
    @RequestMapping(value = "/downgradeSeat/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> downgradeSeat(@PathVariable Long id) {
        try{
            Optional<Booking> downgraded = bookingService.downgradeSeat(id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Booking downgraded successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    //reading
    @GetMapping("/readBooking/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Booking>> getBooking(@PathVariable Long id) {
        try{
            Optional<Booking> booking = bookingService.getBooking(id);
            return new ResponseEntity<Optional<Booking>>(booking, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Optional<Booking>>(HttpStatus.BAD_REQUEST);
        }
//        return bookingService.getBooking(id);
    }

    @GetMapping("/readAllBookings")
    @ResponseBody
    public ResponseEntity<List<Booking>> getAllBookings() {
        try{
            List<Booking> allBookings = bookingService.getAllBookings();
            return new ResponseEntity<List<Booking>>(allBookings, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<List<Booking>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readAllCustomerBookings/{id}")
    @ResponseBody
    public ResponseEntity<List<Booking>> getAllCustomerBookings(@PathVariable Long id) {
        try{
            List<Booking> customersBookings= bookingService.getAllCustomerBookings(id);
            return new ResponseEntity<List<Booking>>(customersBookings, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<List<Booking>>(HttpStatus.BAD_REQUEST);
        }
    }
}
