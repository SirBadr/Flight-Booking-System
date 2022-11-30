package com.example.demo.Flight;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table //to create a table in database
public class Flight {
    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_sequence"
    )
    private Long Id;
    private String flightNumber;
    private String airLine; //convert to Airline class in refactoring!
    private Integer fare; // ??
    private String origin;
    private String dest;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Timestamp departureTime;
    private Timestamp arrivalTime;

    public Flight() {
    }

    public Flight(Long id, String flightNumber, String airLine, Integer fare, String origin, String dest, LocalDate departureDate, LocalDate arrivalDate, Timestamp departureTime, Timestamp arrivalTime) {
        Id = id;
        this.flightNumber = flightNumber;
        this.airLine = airLine;
        this.fare = fare;
        this.origin = origin;
        this.dest = dest;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Flight(String flightNumber, String airLine, Integer fare, String origin, String dest, LocalDate departureDate, LocalDate arrivalDate, Timestamp departureTime, Timestamp arrivalTime) {
        this.flightNumber = flightNumber;
        this.airLine = airLine;
        this.fare = fare;
        this.origin = origin;
        this.dest = dest;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "Id=" + Id +
                ", flightNumber='" + flightNumber + '\'' +
                ", airLine='" + airLine + '\'' +
                ", fare=" + fare +
                ", origin='" + origin + '\'' +
                ", dest='" + dest + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
