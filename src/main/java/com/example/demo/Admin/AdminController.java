package com.example.demo.Admin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.APIResponses.APIResponses;
import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightService;
import com.example.demo.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.stream.Collectors;

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
    public ResponseEntity<APIResponses> adminSignUp(
            @RequestBody Admin newAdmin
    ) {
        try{
            if(adminService.adminExists(newAdmin.getEmail())){
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Admin Already Exists"), HttpStatus.OK);
            }
            adminService.adminSignUp(newAdmin);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Admin Created Successfully"), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, "Error Occurred") ,HttpStatus.BAD_REQUEST);
        }
    }

    class Resp {
        public String accessToken;
        public Resp(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> adminSignIn(@RequestBody Admin adm) {
//        System.out.println(email);
        var admin = adminService.adminSignIn(adm.getEmail());
//        System.out.println(admin);
        if(admin == null) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, "Admin does not exist") ,HttpStatus.OK);
        }else{
            Algorithm algorithm = Algorithm.HMAC256("brightSkies".getBytes());
            String accessToken = JWT.create()
                    .withSubject(admin.getEmail())
                    .withClaim("roles", admin.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
            return new ResponseEntity<Resp>(new Resp(accessToken), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addFlight/{token}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> adminAddFlight (@RequestBody Flight flight, @PathVariable String token) {
        try{
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            if(!payload.contains("ADMIN_ROLE")) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "You are not authorized to add flight, please use admin access token if you have") ,HttpStatus.UNAUTHORIZED);
            }
            var res = flightService.adminAddFlight(flight);
//            System.out.println(res);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Flight added successfully"), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, "Error Occurred") ,HttpStatus.BAD_REQUEST);
        }
    }

    // Flight Update APIs
    @RequestMapping(value = "/updateFlightNumber/{id}/{token}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> adminUpdateFlightNumber (@RequestBody Flight flight, @PathVariable Long id, @PathVariable String token) {
        try{
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            if(!payload.contains("ADMIN_ROLE")) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "You are not authorized to update flight, please use admin access token if you have") ,HttpStatus.UNAUTHORIZED);
            }
            if(!flightService.flightExists(id)) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Flight does not exist!") ,HttpStatus.OK);
            }
            flightService.adminUpdateFlightNumber(flight.getFlightNumber(), id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Flight Updated successfully") ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, "Error Occurred") ,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightFare/{id}/{token}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> adminUpdateFlightFare (@RequestBody Flight flight, @PathVariable Long id, @PathVariable String token) {
        try{
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            if(!payload.contains("ADMIN_ROLE")) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "You are not authorized to update flight, please use admin access token") ,HttpStatus.UNAUTHORIZED);
            }
            if(!flightService.flightExists(id)) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Flight does not exist") ,HttpStatus.OK);
            }
            flightService.adminUpdateFlightFare(flight.getFare(), id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Flight updated successfully") ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, "Error Occurred") ,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightOrigin/{id}/{token}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> adminUpdateFlightOrigin (@RequestBody Flight flight, @PathVariable Long id, @PathVariable String token) {
        try{
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            if(!payload.contains("ADMIN_ROLE")) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "You are not authorized to update flight, please use admin access keys") ,HttpStatus.UNAUTHORIZED);
            }
            if(!flightService.flightExists(id)) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Flight does not exist") ,HttpStatus.OK);
            }
            flightService.adminUpdateFlightOrigin(flight.getOrigin(), id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Flight updated successfully") ,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightDest/{id}/{token}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> adminUpdateFlightDest (@RequestBody Flight flight, @PathVariable Long id, @PathVariable String token) {
        try{
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            if(!payload.contains("ADMIN_ROLE")) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "You are not authorized to update flight, please use admin access keys") ,HttpStatus.UNAUTHORIZED);
            }
            if(!flightService.flightExists(id)) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Flight does not exist") ,HttpStatus.OK);
            }
            flightService.adminUpdateFlightDest(flight.getDest(), id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Flight updated successfully") ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightDepartureDate/{id}/{token}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<APIResponses> adminUpdateFlightDepartureDate (@RequestBody Flight flight, @PathVariable Long id, @PathVariable String token) {
        try{
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            if(!payload.contains("ADMIN_ROLE")) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "You are not authorized to update flight, please use admin access keys") ,HttpStatus.UNAUTHORIZED);
            }
            if(!flightService.flightExists(id)) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Flight does not exist") ,HttpStatus.OK);
            }
            flightService.adminUpdateFlightDepartureDate(flight.getDepartureDate(), id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Flight updated successfully") ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateFlightArrivalDate/{id}/{token}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> adminUpdateFlightArrivalDate (@RequestBody Flight flight, @PathVariable Long id, @PathVariable String token) {
        try{
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            if(!payload.contains("ADMIN_ROLE")) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "You are not authorized to update flight, please use admin access keys if you have") ,HttpStatus.UNAUTHORIZED);
            }
            if(!flightService.flightExists(id)) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Flight does not exist") ,HttpStatus.OK);
            }
            flightService.adminUpdateFlightArrivalDate(flight.getArrivalDate(), id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Flight updated successfully") ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    //adminRemoveFlight
    @RequestMapping(value = "/removeFlight/{id}/{token}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> adminRemoveFlight (@PathVariable Long id, @PathVariable String token) {
        try{
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            if(!payload.contains("ADMIN_ROLE")) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "You are not authorized to remove flight, please use admin access keys"), HttpStatus.UNAUTHORIZED);
            }
            if(!flightService.flightExists(id)) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "Flight does not exist") ,HttpStatus.OK);
            }
            flightService.adminRemoveFlight(id);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Flight removed successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
