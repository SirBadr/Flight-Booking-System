package com.example.demo.customer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.APIResponses.APIResponses;
import com.example.demo.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> customerSignUp(
        @RequestBody Customer newCustomer
    ) {
        try{
            if(customerService.customerExists(newCustomer.getEmail())) {
                // if customer already exists, sign up fails!
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Customer already exists") ,HttpStatus.BAD_REQUEST);
            }
            customerService.customerSignUp(newCustomer);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Customer Created successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> customerSignIn(@RequestBody Customer cus) {
//        System.out.println(email);
        var customer = customerService.customerSignIn(cus.getEmail());
        System.out.println(customer);
        if(customer == null) {
            // there's no customer saved to sign in with !
            return new ResponseEntity<APIResponses>(new APIResponses(false, "Customer does not exist") ,HttpStatus.NOT_FOUND);
        }else{
            // create JWT token with roles of this user
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
