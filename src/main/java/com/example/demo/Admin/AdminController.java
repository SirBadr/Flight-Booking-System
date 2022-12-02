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
    public ResponseEntity<Admin> adminSignUp(
            @RequestBody Admin newAdmin
    ) {
        try{
            adminService.adminSignUp(newAdmin);
            return new ResponseEntity<Admin>(newAdmin, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Admin>(HttpStatus.BAD_REQUEST);
        }
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
        try{
            var res = flightService.adminAddFlight(flight);
//            System.out.println(res);
            return new ResponseEntity<Flight>(res, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
        }
    }

    // Flight Update APIs
    @RequestMapping(value = "/updateFlightNumber/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightNumber (@RequestBody Flight flight, @PathVariable Long id) {
        try{
            flightService.adminUpdateFlightNumber(flight.getFlightNumber(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightFare/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightFare (@RequestBody Flight flight, @PathVariable Long id) {
        try{
            flightService.adminUpdateFlightFare(flight.getFare(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightOrigin/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightOrigin (@RequestBody Flight flight, @PathVariable Long id) {
        try{
            flightService.adminUpdateFlightOrigin(flight.getOrigin(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightDest/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightDest (@RequestBody Flight flight, @PathVariable Long id) {
        try{
            flightService.adminUpdateFlightDest(flight.getDest(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightDepartureDate/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightDepartureDate (@RequestBody Flight flight, @PathVariable Long id) {
        try{
            flightService.adminUpdateFlightDepartureDate(flight.getDepartureDate(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightArrivalDate/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlightArrivalDate (@RequestBody Flight flight, @PathVariable Long id) {
        try{
            flightService.adminUpdateFlightArrivalDate(flight.getArrivalDate(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
        }
    }

    //adminRemoveFlight
    @RequestMapping(value = "/removeFlight/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Flight> adminRemoveFlight (@PathVariable Long id) {
        try{
            flightService.adminRemoveFlight(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
        }
    }


}
