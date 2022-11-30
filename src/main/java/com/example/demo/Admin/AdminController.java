package com.example.demo.Admin;

import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/Admins")
public class AdminController {
    private final AdminService adminService;
    private final FlightService flightService;
    @Autowired
    public AdminController(AdminService adminService, FlightService flightService) {
        this.flightService = flightService;
        this.adminService = adminService;
    }

    @PostMapping("/signUp")
    public void adminSignUp(
            @RequestBody Admin newAdmin
    ) {
        adminService.adminSignUp(newAdmin);
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Admin> adminSignIn(@RequestBody Admin adm) {
//        System.out.println(email);
        var admin = adminService.adminSignIn(adm.getEmail());
        System.out.println(admin);
        if(admin == null) {
            return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Admin>(admin, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addFlight", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Flight> adminAddFlight (@RequestBody Flight flight) {
//        System.out.println(flight);
        var res = flightService.adminAddFlight(flight);
        System.out.println(res);
        return new ResponseEntity<Flight>(res, HttpStatus.OK);
    }

    // Flight Update APIs
    @RequestMapping(value = "/updateFlightNumber/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightNumber (@RequestBody Flight flight, @PathVariable Long id) {
        flightService.adminUpdateFlightNumber(flight.getFlightNumber(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateFlightFare/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightFare (@RequestBody Flight flight, @PathVariable Long id) {
        flightService.adminUpdateFlightFare(flight.getFare(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateFlightOrigin/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightOrigin (@RequestBody Flight flight, @PathVariable Long id) {
        flightService.adminUpdateFlightOrigin(flight.getOrigin(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateFlightDest/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightDest (@RequestBody Flight flight, @PathVariable Long id) {
        flightService.adminUpdateFlightDest(flight.getDest(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateFlightDepartureDate/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightDepartureDate (@RequestBody Flight flight, @PathVariable Long id) {
        flightService.adminUpdateFlightDepartureDate(flight.getDepartureDate(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateFlightArrivalDate/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightArrivalDate (@RequestBody Flight flight, @PathVariable Long id) {
        flightService.adminUpdateFlightArrivalDate(flight.getArrivalDate(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //adminRemoveFlight
    @RequestMapping(value = "/removeFlight/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Flight> adminRemoveFlight (@PathVariable Long id) {
        flightService.adminRemoveFlight(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
