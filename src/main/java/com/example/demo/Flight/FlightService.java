package com.example.demo.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight adminAddFlight(Flight flight) {
        flightRepository.save(flight);
        return flight;
    }

    public Optional<Flight> readFlightById(Long id) {
        var flight = flightRepository.findById(id);
        return flight;
    }

    public List<Flight> readFlightByAirLine(String airLine) {
        return flightRepository.findByAirLine(airLine);
    }

    public List<Flight> readFlightByOrigin(String from) {
        return flightRepository.findByFrom(from);
    }

    public List<Flight> readFlightByDestination(String to) {
        return flightRepository.findByTo(to);
    }

    public List<Flight> readFlightByDepartureDate(Timestamp departureDate) {
        return flightRepository.findByDepartureDate(departureDate);
    }

    public List<Flight> readFlightByArrivalDate(Timestamp arrivalDate) {
        return flightRepository.findByArrivalDate(arrivalDate);
    }

    public List<Flight> readFlightByPriceRange(Integer minFare, Integer maxFare) {
        return flightRepository.findByPriceRange(minFare, maxFare);
    }

    public Flight adminUpdateFlight(Flight flight, Long id) {
        Flight _flight = flightRepository.updateFlight(flight, id);
        return _flight;
    }

    // add admin function to remove flight!.
}
