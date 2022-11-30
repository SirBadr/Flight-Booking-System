package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    }
//                new Customer(
//                        1L,
//                        "Mahmoud",
//                        "mahmoudbadr9199@gmail.com",
//                        LocalDate.of(1999,1,9),
//                        Gender.MALE
//                );
    public void customerSignUp(
        Customer newCustomer
    ) {
        //System.out.println(newCustomer);
        customerRepository.save(
                newCustomer
        );
    }

    public Customer customerSignIn(String email) {
        System.out.println(email);
        var result = customerRepository.findByEmail(email);
        System.out.println("RESULT");
        System.out.println(result);
        return result;
    }

}
