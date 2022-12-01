package com.example.demo.Booking;

import jakarta.persistence.*;

import java.util.EnumMap;

@Table
@Entity
public class Booking { // booking operations.
    @Id
    @SequenceGenerator(
            name = "booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booking_sequence"
    )
    private Long id; // reservation id
    private Long flightId; // flight id
    private String seatNumber; // seat number
    private SeatType seatType; // seat type (economy, business)

    public Booking() {
    }

    public Booking(Long flightId, String seatNumber, SeatType seatType) {
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
    }

    public Booking(Long id, Long flightId, String seatNumber, SeatType seatType) {
        this.id = id;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
