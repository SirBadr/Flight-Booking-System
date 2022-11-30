package com.example.demo.Flight;

import com.example.demo.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/readFlightByOrigin/{from}")
    @ResponseBody
    public List<Flight> getFlightsByOrigin(@PathVariable String from) {
        return flightService.readFlightByOrigin(from);
    }

    @GetMapping("/readFlightByOrigin/{to}")
    @ResponseBody
    public List<Flight> getFlightsByDestination(@PathVariable String to) {
        return flightService.readFlightByDestination(to);
    }

    @GetMapping("/readFlightByDepartureDate/{departureDate}")
    @ResponseBody
    public List<Flight> getFlightByDepartureDate(@PathVariable Timestamp departureDate) {
        return flightService.readFlightByDepartureDate(departureDate);
    }

    @GetMapping("/readFlightByArrivalDate/{arrivalDate}")
    @ResponseBody
    public List<Flight> getFlightByArrivalDate(@PathVariable Timestamp arrivalDate) {
        return flightService.readFlightByArrivalDate(arrivalDate);
    }

    @GetMapping("/readFlightByPriceRange/{minFare}/{maxFare}")
    @ResponseBody
    public List<Flight> getFlightByPriceRange(@PathVariable Integer minFare, @PathVariable Integer maxFare) {
        return flightService.readFlightByPriceRange(minFare, maxFare);
    }

}
