package com.example.demo.Booking;

import com.example.demo.customer.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "SELECT * FROM Booking b WHERE b.customer_id=?1", nativeQuery = true)
    List<Booking> findByCustomerId(Long customerId);
    @Modifying
    @Query("UPDATE Booking b set b.seatType=?1 where b.customerId=?2")
    void changeSeatType(SeatType seatType, Long id);
}
