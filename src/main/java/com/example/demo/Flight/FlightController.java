package com.example.demo.Flight;

import com.example.demo.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public Optional<Flight> readFlightById(@PathVariable Long id) {
        return flightService.readFlightById(id);
    }

    @GetMapping("/readAllFlights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/readAllFlightsByAirLine/{airLine}")
    @ResponseBody
    public List<Flight> getFlightsByAirLine(@PathVariable String airLine) {
        return flightService.readFlightByAirLine(airLine);
    }

    @GetMapping("/readFlightByOrigin/{origin}")
    @ResponseBody
    public List<Flight> getFlightsByOrigin(@PathVariable String origin) {
        System.out.println(origin);
        return flightService.readFlightByOrigin(origin);
    }

    @GetMapping("/readFlightByDestination/{dest}")
    @ResponseBody
    public List<Flight> getFlightsByDestination(@PathVariable String dest) {
        return flightService.readFlightByDestination(dest);
    }

    @GetMapping("/readFlightByDepartureDate")
    @ResponseBody
    public List<Flight> getFlightByDepartureDate(@RequestBody Flight flight) {
        return flightService.readFlightByDepartureDate(flight.getDepartureDate());
    }

    @GetMapping("/readFlightByArrivalDate")
    @ResponseBody
    public List<Flight> getFlightByArrivalDate(@RequestBody Flight flight) {
        return flightService.readFlightByArrivalDate(flight.getArrivalDate());
    }

    @GetMapping("/readFlightByPriceRange/{minFare}/{maxFare}")
    @ResponseBody
    public List<Flight> getFlightByPriceRange(@PathVariable Integer minFare, @PathVariable Integer maxFare) {
        return flightService.readFlightByPriceRange(minFare, maxFare);
    }

}
