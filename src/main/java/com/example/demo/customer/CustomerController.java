package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void customerSignUp(
        @RequestBody Customer newCustomer
    ) {
        customerService.customerSignUp(newCustomer);
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
