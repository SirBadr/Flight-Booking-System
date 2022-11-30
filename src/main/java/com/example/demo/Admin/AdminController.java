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

    @RequestMapping(value = "/updateFlight/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Flight> adminUpdateFlight (@RequestBody Flight flight, @PathVariable Long id) {
        flightService.adminUpdateFlight(flight, id);
        return new ResponseEntity<Flight>(flight, HttpStatus.OK);
    }

    //adminRemoveFlight
    @RequestMapping(value = "/removeFlight/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Flight> adminRemoveFlight (@PathVariable Long id) {
        flightService.adminRemoveFlight(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
