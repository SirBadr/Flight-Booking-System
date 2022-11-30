package com.example.demo.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Modifying
    @Query("update Flight f set f.flightNumber = ?1 where f.id = ?2")
    void updateFlightNumber(String flightNumber, Long id);
    @Modifying
    @Query("update Flight f set f.fare = ?1 where f.id = ?2")
    void updateFlightFare(Integer fare, Long id);
    @Modifying
    @Query("update Flight f set f.origin = ?1 where f.id = ?2")
    void updateFlightOrigin(String origin, Long id);
    @Modifying
    @Query("update Flight f set f.dest = ?1 where f.id = ?2")
    void updateFlightDest(String dest, Long id);
    @Modifying
    @Query("update Flight f set f.departureDate = ?1 where f.id = ?2")
    void updateFlightDepartureDate(LocalDate departureDate, Long id);
    @Modifying
    @Query("update Flight f set f.arrivalDate = ?1 where f.id = ?2")
    void updateFlightArrivalDate(LocalDate arrivalDate, Long id);

}
