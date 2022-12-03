package com.example.demo.Flight;

import com.example.demo.customer.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

class FlightServiceTest {
    @Mock
    private FlightRepository flightRepository;
    private AutoCloseable autoCloseable;
    private FlightService underTest;

//    @BeforeAll
//    static void beforeAll() {
//        LocalDate tim = LocalDate.now();
//        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
//        Flight flight = new Flight(
//                "1A",
//                "QATARAIRLINE",
//                120,
//                "ALX",
//                "SYD",
//                tim,
//                tim,
//                tis,
//                tis
//        );
//    }

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new FlightService(flightRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllFlights() {
        underTest.getAllFlights();

        verify(flightRepository).findAll();
    }

    @Test
    void readFlightById() {
        underTest.readFlightById(1L);

        verify(flightRepository).findById(1L);
    }

    @Test
    void readFlightByAirLine() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.readFlightByAirLine(flight.getAirLine());

        verify(flightRepository).findByAirLine(flight.getAirLine());
    }

    @Test
    void readFlightByOrigin() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.readFlightByOrigin(flight.getOrigin());

        verify(flightRepository).findByOrigin(flight.getOrigin());

    }

    @Test
    void readFlightByDestination() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.readFlightByDestination(flight.getDest());

        verify(flightRepository).findByDest(flight.getDest());
    }

    @Test
    void readFlightByDepartureDate() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.readFlightByDepartureDate(flight.getDepartureDate());

        verify(flightRepository).findByDepartureDate(flight.getDepartureDate());

    }

    @Test
    void readFlightByArrivalDate() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.readFlightByArrivalDate(flight.getArrivalDate());

        verify(flightRepository).findByArrivalDate(flight.getArrivalDate());

    }

    @Test
    void readFlightByPriceRange() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.readFlightByPriceRange(120,130);

        verify(flightRepository).findByPriceRange(120,130);

    }

    @Test
    void adminAddFlight() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.adminAddFlight(flight);

        verify(flightRepository).save(flight);

    }

    @Test
    void adminUpdateFlightNumber() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.adminUpdateFlightNumber(flight.getFlightNumber(), 1L);

        verify(flightRepository).updateFlightNumber(flight.getFlightNumber(), 1L);

    }

    @Test
    void adminUpdateFlightFare() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.adminUpdateFlightFare(120, 1L);

        verify(flightRepository).updateFlightFare(120, 1L);

    }

    @Test
    void adminUpdateFlightOrigin() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.adminUpdateFlightOrigin("XYZ", 1L);

        verify(flightRepository).updateFlightOrigin("XYZ", 1L);

    }

    @Test
    void adminUpdateFlightDest() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.adminUpdateFlightDest("XYZ", 1L);

        verify(flightRepository).updateFlightDest("XYZ", 1L);

    }

    @Test
    void adminUpdateFlightDepartureDate() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.adminUpdateFlightDepartureDate(tim, 1L);

        verify(flightRepository).updateFlightDepartureDate(tim, 1L);

    }

    @Test
    void adminUpdateFlightArrivalDate() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.adminUpdateFlightArrivalDate(tim, 1L);

        verify(flightRepository).updateFlightArrivalDate(tim, 1L);

    }

    @Test
    void adminRemoveFlight() {
        LocalDate tim = LocalDate.now();
        Timestamp tis = Timestamp.valueOf(LocalDateTime.now());
        Flight flight = new Flight(
                "1A",
                "QATARAIRLINE",
                120,
                "ALX",
                "SYD",
                tim,
                tim,
                tis,
                tis
        );
        underTest.adminRemoveFlight(1L);

        verify(flightRepository).deleteById(1L);
    }
}