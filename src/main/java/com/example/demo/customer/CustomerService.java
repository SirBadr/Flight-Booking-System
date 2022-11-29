package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
//                new Customer(
//                        1L,
//                        "Mahmoud",
//                        "mahmoudbadr9199@gmail.com",
//                        LocalDate.of(1999,1,9),
//                        Gender.MALE
//                );
    }

}
