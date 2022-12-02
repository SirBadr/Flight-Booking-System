package com.example.demo.Flight;

import ch.qos.logback.core.joran.action.TimestampAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FlightRepositoryTest {
    @Autowired
    private FlightRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByAirLine() {
        // given
        Flight flightA = new Flight();
        flightA.setAirLine("QATARAIRWAYS");
        Flight flightB = new Flight();
        flightB.setAirLine("QATARAIRWAYS");

        underTest.save(flightA);
        underTest.save(flightB);

        // where
        List<Flight> _flight = underTest.findByAirLine("QATARAIRWAYS");

        // then
        assertThat(_flight.size()).isEqualTo(2);
    }

    @Test
    void findByOrigin() {
        // given
        Flight flightA = new Flight();
        flightA.setOrigin("ALX");

        underTest.save(flightA);
        // where
        List<Flight> _flight = underTest.findByOrigin("ALX");
        List<Flight> _flight2 = underTest.findByOrigin("NYC");

        // then
        assertThat(_flight.size()).isEqualTo(1);
        assertThat(_flight2.size()).isEqualTo(0);
    }

    @Test
    void findByDest() {
        // given
        Flight flightA = new Flight();
        flightA.setDest("ALX");

        underTest.save(flightA);
        // where
        List<Flight> _flight = underTest.findByDest("ALX");
        List<Flight> _flight2 = underTest.findByOrigin("NYC");

        // then
        assertThat(_flight.size()).isEqualTo(1);
        assertThat(_flight2.size()).isEqualTo(0);

    }

    @Test
    void findByDepartureDate() {
        // given
        LocalDate tim = LocalDate.now();
        Flight flightA = new Flight();
        flightA.setDepartureDate(tim);

        underTest.save(flightA);
        // where
        List<Flight> _flight = underTest.findByDepartureDate(tim);

        // then
        assertThat(_flight.get(0).getDepartureDate()).isEqualTo(tim);
    }

    @Test
    void findByArrivalDate() {
        // given
        LocalDate tim = LocalDate.now();
        Flight flightA = new Flight();
        flightA.setArrivalDate(tim);

        underTest.save(flightA);
        // where
        List<Flight> _flight = underTest.findByArrivalDate(tim);

        // then
        assertThat(_flight.get(0).getArrivalDate()).isEqualTo(tim);
    }

    @Test
    void findByPriceRange() {
        // given
        Flight flightA = new Flight();
        flightA.setFare(120);
        Flight flightB = new Flight();
        flightB.setFare(130);
        Flight flightC = new Flight();
        flightC.setFare(140);
        underTest.save(flightA);
        underTest.save(flightB);
        underTest.save(flightC);

        // where
        List<Flight> _flightA = underTest.findByPriceRange(125,135);
        List<Flight> _flightB = underTest.findByPriceRange(0,100);
        List<Flight> _flightC = underTest.findByPriceRange(100,125);
        List<Flight> _flightD = underTest.findByPriceRange(0,150);
        List<Flight> _flightE = underTest.findByPriceRange(125,150);

        // then
        assertThat(_flightA.size()).isEqualTo(1);
        assertThat(_flightB.size()).isEqualTo(0);
        assertThat(_flightC.size()).isEqualTo(1);
        assertThat(_flightD.size()).isEqualTo(3);
        assertThat(_flightE.size()).isEqualTo(2);

    }
}