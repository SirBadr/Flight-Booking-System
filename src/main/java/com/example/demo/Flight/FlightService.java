package com.example.demo.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public Optional<Flight> readFlightById(Long id) {
        var flight = flightRepository.findById(id);
        return flight;
    }

    public List<Flight> readFlightByAirLine(String airLine) {
        return flightRepository.findByAirLine(airLine);
    }

    public List<Flight> readFlightByOrigin(String origin) {
        return flightRepository.findByOrigin(origin);
    }

    public List<Flight> readFlightByDestination(String to) {
        return flightRepository.findByDest(to);
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

    public Flight adminAddFlight(Flight flight) {
        var _flight = flightRepository.save(flight);
        return _flight;
    }

    public Flight adminUpdateFlight(Flight flight, Long id) {
        Flight _flight = flightRepository.updateFlight(flight, id);
        return _flight;
    }

    public boolean adminRemoveFlight(Long id) {
        flightRepository.deleteById(id);
        return true;
    }
}
