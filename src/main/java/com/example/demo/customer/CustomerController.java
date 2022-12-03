package com.example.demo.customer;

import com.example.demo.Booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Customers")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/signUp")
    public ResponseEntity<Customer> customerSignUp(
        @RequestBody Customer newCustomer
    ) {
        try{
            System.out.println(newCustomer);
            customerService.customerSignUp(newCustomer);
            return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Customer> customerSignIn(@RequestBody Customer cus) {
//        System.out.println(email);
        var customer = customerService.customerSignIn(cus.getEmail());
        System.out.println(customer);
        if(customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }
    }
}
