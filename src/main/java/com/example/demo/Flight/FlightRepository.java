package com.example.demo.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository
        extends JpaRepository<Flight, Long> {

    List<Flight> findByAirLine(String airLine);
    List<Flight> findByOrigin(String origin);
    List<Flight> findByDest(String dest);
    List<Flight> findByDepartureDate(Timestamp departureDate);
    List<Flight> findByArrivalDate(Timestamp arrivalDate);

    @Query("SELECT f FROM Flight f WHERE f.fare > ?1 and f.fare < ?2")
    List<Flight> findByPriceRange(Integer minFare, Integer maxFare);

    @Query("update Flight f set f = ?1 where f.id = ?2")
    Flight updateFlight(Flight flight, Long id);
}
