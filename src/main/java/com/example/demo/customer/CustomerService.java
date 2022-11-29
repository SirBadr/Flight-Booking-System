package com.example.demo.customer;

import java.time.LocalDate;
import java.util.List;

public class CustomerService {

    public List<Customer> getCustomers() {
        return List.of(
                new Customer(
                        1L,
                        "Mahmoud",
                        "mahmoudbadr9199@gmail.com",
                        LocalDate.of(1999,1,9),
                        Gender.MALE
                )
        );
    }
}
