package com.example.demo.customer;

import com.example.demo.Booking.BookingRepository;
import com.example.demo.Booking.BookingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    private AutoCloseable autoCloseable;
    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getCustomers() {
        LocalDate tim = LocalDate.now();
        Customer customer = new Customer(
                "customer",
                "customer@gmail.com",
                tim,
                Gender.FEMALE
        );
        underTest.getCustomers();

        verify(customerRepository).findAll();
    }

    @Test
    void customerSignUp() {
        LocalDate tim = LocalDate.now();
        Customer customer = new Customer(
                "customer",
                "customer@gmail.com",
                tim,
                Gender.FEMALE
        );
        underTest.customerSignUp(customer);

        verify(customerRepository).save(customer);
    }

    @Test
    void customerSignIn() {
        LocalDate tim = LocalDate.now();
        Customer customer = new Customer(
                "customer",
                "customer@gmail.com",
                tim,
                Gender.FEMALE
        );
        underTest.customerSignIn(customer.getEmail());

        verify(customerRepository).findByEmail(customer.getEmail());
    }
}