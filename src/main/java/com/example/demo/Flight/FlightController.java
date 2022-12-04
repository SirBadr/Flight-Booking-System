package com.example.demo.Flight;

import com.example.demo.APIResponses.APIResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Flight")
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/readFlight/{id}")
    @ResponseBody
    public ResponseEntity<?> readFlightById(@PathVariable Long id) {
        try{
            Optional<Flight> flight = flightService.readFlightById(id);
            if(flight.get() == null) {
                // no flights
                return new ResponseEntity<APIResponses>(new APIResponses(true, "No flight exists with this Id"), HttpStatus.OK);
            }
            return new ResponseEntity<Optional<Flight>>(flight, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readAllFlights")
    public ResponseEntity<?> getAllFlights() {
        try{
            List<Flight> allFlights = flightService.getAllFlights();
            if(allFlights.size() == 0) {
                // no flights
                return new ResponseEntity<APIResponses>(new APIResponses(true, "No flights yet"), HttpStatus.OK);
            }
            return new ResponseEntity<List<Flight>>(allFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readAllFlightsByAirLine/{airLine}")
    @ResponseBody
    public ResponseEntity<?> getFlightsByAirLine(@PathVariable String airLine) {
        try{
            List<Flight> airLineFlights = flightService.readFlightByAirLine(airLine);
            if(airLineFlights.size() == 0) {
                // no flights
                return new ResponseEntity<APIResponses>(new APIResponses(true, "No Flights"), HttpStatus.OK);
            }
            return new ResponseEntity<List<Flight>>(airLineFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByOrigin/{origin}")
    @ResponseBody
    public ResponseEntity<?> getFlightsByOrigin(@PathVariable String origin) {
        try{
            List<Flight> originFlights = flightService.readFlightByOrigin(origin);
            if(originFlights.size() == 0) {
                // no flights
                return new ResponseEntity<APIResponses>(new APIResponses(true, "No Flights"), HttpStatus.OK);
            }
            return new ResponseEntity<List<Flight>>(originFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Flight>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByDestination/{dest}")
    @ResponseBody
    public ResponseEntity<?> getFlightsByDestination(@PathVariable String dest) {
        try{
            List<Flight> destinationFlights = flightService.readFlightByDestination(dest);
            if(destinationFlights.size() == 0) {
                // no flights
                return new ResponseEntity<APIResponses>(new APIResponses(true, "No Flights"), HttpStatus.OK);
            }
            return new ResponseEntity<List<Flight>>(destinationFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByDepartureDate")
    @ResponseBody
    public ResponseEntity<?> getFlightByDepartureDate(@RequestBody Flight flight) {
        try{
            List<Flight> departureDateFlights = flightService.readFlightByDepartureDate(flight.getDepartureDate());
            if(departureDateFlights.size() == 0) {
                // no flights
                return new ResponseEntity<APIResponses>(new APIResponses(true, "No Flights") ,HttpStatus.OK);
            }
            return new ResponseEntity<List<Flight>>(departureDateFlights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByArrivalDate")
    @ResponseBody
    public ResponseEntity<?> getFlightByArrivalDate(@RequestBody Flight flight) {
        try{
            List<Flight> arrivalDateFlights = flightService.readFlightByArrivalDate(flight.getArrivalDate());
            if(arrivalDateFlights.size()==0){
                // no flights
                return new ResponseEntity<APIResponses>(new APIResponses(true, "No Flights") ,HttpStatus.OK);
            }
            return new ResponseEntity<List<Flight>>(arrivalDateFlights, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/readFlightByPriceRange/{minFare}/{maxFare}")
    @ResponseBody
    public ResponseEntity<?> getFlightByPriceRange(@PathVariable Integer minFare, @PathVariable Integer maxFare) {
        try{
            List<Flight> priceRangeFlights = flightService.readFlightByPriceRange(minFare, maxFare);
            if(priceRangeFlights.size()==0) {
                // no flights
                return new ResponseEntity<APIResponses>(new APIResponses(true, "No Flights") ,HttpStatus.OK);
            }
            return new ResponseEntity<List<Flight>>(priceRangeFlights, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

}
