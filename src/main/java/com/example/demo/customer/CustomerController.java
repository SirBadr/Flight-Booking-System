package com.example.demo.customer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.Booking.Booking;
import com.example.demo.Role.Role;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            if(customerService.customerExists(newCustomer.getEmail())) {
                System.out.println("HELLo");
                return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
            }
            customerService.customerSignUp(newCustomer);
            return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Resp> customerSignIn(@RequestBody Customer cus) {
//        System.out.println(email);
        var customer = customerService.customerSignIn(cus.getEmail());
        System.out.println(customer);
        if(customer == null) {
            return new ResponseEntity<Resp>(HttpStatus.NOT_FOUND);
        }else{
            Algorithm algorithm = Algorithm.HMAC256("brightSkies".getBytes());
            String accessToken = JWT.create()
                    .withSubject(customer.getEmail())
//                    .withClaim("roles", customer.getRoles().stream().collect(Collectors.toList()))
                    .withClaim("roles", customer.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
            return new ResponseEntity<Resp>(new Resp(accessToken), HttpStatus.OK);
        }
    }
    class Resp {
        public String accessToken;
        public Resp(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
