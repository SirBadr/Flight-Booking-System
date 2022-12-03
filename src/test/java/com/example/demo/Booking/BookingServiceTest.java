package com.example.demo.Booking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;
    private AutoCloseable autoCloseable;
    private BookingService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new BookingService(bookingRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void bookFlight() {
        //when
        Booking booking  = new Booking(
                1L,
                "12A",
                SeatType.ECONOMY,
                2L
        );
        underTest.bookFlight(booking);

        // then
        verify(bookingRepository).save(booking);
    }

    @Test
    void upgradeSeat() {
        Booking booking  = new Booking(
                1L,
                "12A",
                SeatType.ECONOMY,
                2L
        );
        underTest.upgradeSeat(2L);

        verify(bookingRepository).changeSeatType(SeatType.BUSINESS, 2L);
    }

    @Test
    void downgradeSeat() {
        Booking booking  = new Booking(
                1L,
                "12A",
                SeatType.BUSINESS,
                2L
        );
        underTest.downgradeSeat(2L);

        verify(bookingRepository).changeSeatType(SeatType.ECONOMY, 2L);
    }

    @Test
    void getBooking() {
        Booking booking  = new Booking(
                1L,
                "12A",
                SeatType.BUSINESS,
                2L
        );
        underTest.getBooking(1L);

        verify(bookingRepository).findById(1L);
    }

    @Test
    void getAllBookings() {
        Booking booking  = new Booking(
                1L,
                "12A",
                SeatType.BUSINESS,
                2L
        );
        underTest.getAllBookings();

        verify(bookingRepository).findAll();
    }

    @Test
    void getAllCustomerBookings() {
        Booking booking  = new Booking(
                1L,
                "12A",
                SeatType.BUSINESS,
                2L
        );
        underTest.getAllCustomerBookings(2L);

        verify(bookingRepository).findByCustomerId(2L);

    }
}