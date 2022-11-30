package com.example.demo.Flight;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Transactional
    public boolean adminUpdateFlightNumber(String flightNumber, Long id) {
        flightRepository.updateFlightNumber(flightNumber, id);
        return true;
    }
    @Transactional
    public boolean adminUpdateFlightFare(Integer fare, Long id) {
        flightRepository.updateFlightFare(999, id);
        return true;
    }
    @Transactional
    public boolean adminUpdateFlightOrigin(String origin, Long id) {
        flightRepository.updateFlightOrigin(origin, id);
        return true;
    }
    @Transactional
    public boolean adminUpdateFlightDest(String dest, Long id) {
        flightRepository.updateFlightDest(dest, id);
        return true;
    }
    @Transactional
    public boolean adminUpdateFlightDepartureDate(LocalDate departureDate, Long id) {
        flightRepository.updateFlightDepartureDate(departureDate, id);
        return true;
    }
    @Transactional
    public boolean adminUpdateFlightArrivalDate(LocalDate arrivalDate, Long id) {
        flightRepository.updateFlightArrivalDate(arrivalDate, id);
        return true;
    }

    public boolean adminRemoveFlight(Long id) {
        flightRepository.deleteById(id);
        return true;
    }
}
