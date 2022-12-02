package com.example.demo.Flight;

import com.example.demo.Admin.Admin;
import com.example.demo.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Flight")
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/readFlight/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Flight>> readFlightById(@PathVariable Long id) {
        try{
            Optional<Flight> flight = flightService.readFlightById(id);
            return new ResponseEntity<Optional<Flight>>(flight, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Optional<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readAllFlights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        try{
            List<Flight> allFlights = flightService.getAllFlights();
            return new ResponseEntity<List<Flight>>(allFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readAllFlightsByAirLine/{airLine}")
    @ResponseBody
    public ResponseEntity<List<Flight>> getFlightsByAirLine(@PathVariable String airLine) {
        try{
            List<Flight> airLineFlights = flightService.readFlightByAirLine(airLine);
            return new ResponseEntity<List<Flight>>(airLineFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByOrigin/{origin}")
    @ResponseBody
    public ResponseEntity<List<Flight>> getFlightsByOrigin(@PathVariable String origin) {
        try{
            List<Flight> originFlights = flightService.readFlightByOrigin(origin);
            return new ResponseEntity<List<Flight>>(originFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByDestination/{dest}")
    @ResponseBody
    public ResponseEntity<List<Flight>> getFlightsByDestination(@PathVariable String dest) {
        try{
            List<Flight> destinationFlights = flightService.readFlightByDestination(dest);
            return new ResponseEntity<List<Flight>>(destinationFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByDepartureDate")
    @ResponseBody
    public ResponseEntity<List<Flight>> getFlightByDepartureDate(@RequestBody Flight flight) {
        try{
            List<Flight> departureDateFlights = flightService.readFlightByDepartureDate(flight.getDepartureDate());
            return new ResponseEntity<List<Flight>>(departureDateFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByArrivalDate")
    @ResponseBody
    public ResponseEntity<List<Flight>> getFlightByArrivalDate(@RequestBody Flight flight) {
        try{
            List<Flight> arrivalDateFlights = flightService.readFlightByArrivalDate(flight.getArrivalDate());
            return new ResponseEntity<List<Flight>>(arrivalDateFlights, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<List<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByPriceRange/{minFare}/{maxFare}")
    @ResponseBody
    public ResponseEntity<List<Flight>> getFlightByPriceRange(@PathVariable Integer minFare, @PathVariable Integer maxFare) {
        try{
            List<Flight> priceRangeFlights = flightService.readFlightByPriceRange(minFare, maxFare);
            return new ResponseEntity<List<Flight>>(priceRangeFlights, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<List<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

}
