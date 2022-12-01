package com.example.demo.Booking;

import com.example.demo.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT * FROM Booking b WHERE b.customerId=?1")
    List<Booking> findByCustomerId(Long customerId);

    @Query("UPDATE Booking b set b.seatType=?1 where b.id=?2")
    void changeSeatType(SeatType seatType, Long id);
}
