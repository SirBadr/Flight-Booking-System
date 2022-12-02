package com.example.demo.Booking;

import com.example.demo.Admin.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookingRepositoryTest {
    @Autowired
    private BookingRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindFlightsByCustomerId() {
        // given
        Booking testA = new Booking(
                1L,
                "12A",
                SeatType.ECONOMY,
                1L
        );

        Booking testB = new Booking(
                1L,
                "12A",
                SeatType.ECONOMY,
                2L
        );

        Booking testC = new Booking(
                1L,
                "12A",
                SeatType.ECONOMY,
                2L
        );
        underTest.save(testA);
        underTest.save(testB);
        underTest.save(testC);

        // when
        List<Booking> customerOneBookings = underTest.findByCustomerId(1L);
        List<Booking> customerTwoBookings = underTest.findByCustomerId(2L);
        List<Booking> customerThreeBookings = underTest.findByCustomerId(3L);

        // then
        assertThat(customerOneBookings.size()).isEqualTo(1);
        assertThat(customerTwoBookings.size()).isEqualTo(2);
        assertThat(customerThreeBookings.size()).isEqualTo(0);
    }
}