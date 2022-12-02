# Flight-Booking-System Challenge

# Challenge Statement
The system provides a facility to search for the available flights between two locations. 
As well, demonstrating fare, departure and arrival times of the different flights.
It allows user to make a reservation of flight and upgrading the ticket class.

# Requirements
1. Set of APIs to CRUD(create, read, update, delete) flights for (Admin)
2. API to search for available flights along with the fare, from and to desitations for (Customers) 
3. API to book a flight ticket, upgrade, cancel it for (Customer).

# Deliverables
1. Documented source code
2. Any configuration needed to run the system (ex. Database connection parameters).

# Bonus
1. cover code with JUnit test cases
2. Authentication and Authorization using Oauth 2.0 or JWT.

# Project scope
This project is a flight booking system will allow admin to add/update/read/remove flights and specifying their details like fare, destination, origin, departure and arrival times.
And will allow customers to search through all flights, by fare, from and to destinations, book a certain flight ticket, upgrade to a higher class, cancel his booking.

Customer's payment for a flight is considered out-of-scope of this project, so payments will not be handled.

## Assumptions.
1. As long as we have `customers` able to book a flight, upgrade or cancel a flight, we need a method to log in.
2. Handling simple sign up / log in only with email ( without password ).

# System Actors
1. Admin: responsible for adding/updating/removing any flight.
2. Customer: responsible for booking, upgrading, cancelling tickets.

# Entities
1. Customer
2. Flight
3. Admin

# classes and enums
```
class Flight {
    Long Id
    String flightNumber
    Airline airLine
    Integer fare
    String origin
    String dest
    LocalDate departureDate
    LocalDate arrivalDate
    TimeStamp departureTime
    TimeStamp arrivalTime
}
```

```
class Airline {
    String Name
    List<Flight> allFlights
}
```

```
Enum seatClass {
    Economy
    Business
}
```

Admin class
```
class Admin {
   Long id
   String name
   String email
}
```

then, create Customer class

```
class Customer {
    Long id
    String name
    String email
    LocalDate birthDate
    Enum gender
}

Enum Gender {
    MALE
    FEMALE
}
```

Customers need to search for a flight & book a seat.
```
class Booking {
   Long id //reservation id
   Long flightId // flight id
   String seatNumber
   SeatType seatType
   Long customerId
}
```

and to search for flights

```
    getAllFlights(){
        return allFlights
    };
```

to search for a specific flight details on a certain day, customer can use `origin`, `dest`, `date`, or just search for a flight with `origin` and `dest`, 
another method is to search for `origin` and `dest` and decide a range of `fare` he'd like to pay like shown in APIs below


now the customer has found a flight he'd like to researve a seat onto.

```
    bookFlight(Booking booking)
```

# Boilerplate
I used <a href="https://start.spring.io/" target="_blank">Initializr</a> to generate a boilerplate code that includes
1. Spring Data JPA: to work with data 
2. Spring Web: helps with creatuing RESTful APIs
3. PostgreSQL Driver: allows Java to connect to a PostgreSQL database.

# steps to connect database
1. install postgres app
2. start postgres server
3. create `flightbooking` database
   1. CREATE DATABASE flightbooking;
   2. GRANT ALL PRIVILEGES ON DATABASE "flightbooking" TO mahmoudsamir;
   3. GRANT ALL PRIVILEGES ON DATABASE "flightbooking" TO postgres;

# APIs
## Customer APIs
1. Sign Up
2. Sign In

## Customer Flight Booking APIs
1. bookFlight
   - Description: Books a new flight to a customer
   - EndPoint: "http://localhost:8080/api/v1/Booking/bookFlight"
   - Request Type: POST
   - Body parameters: Booking booking
   - Path parameters: N/A
   - Returns: N/A
   - Example: ??

2. upgradeSeat
   - Description: upgrades a reserved seat to Business class
   - EndPoint: "http://localhost:8080/api/v1/Booking/upgradeSeat/{id}"
   - Request Type: PUT
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??

3. downgradeSeat
   - Description: downgrades a reserved seat to Economy class
   - EndPoint: "http://localhost:8080/api/v1/Booking/downgradeSeat/{id}"
   - Request Type: PUT
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??


## Admin APIs
1. Sign Up
2. Sign In

### Admin APIs for Flights
## Adding a Flight
1. adminAddFlight
   - Description: adds a new flight to DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/addFlight"
   - Request Type: POST
   - Body parameters: Flight
   - Path parameters: N/A
   - Returns: N/A
   - Example: ??

## Updating a Flight
1. adminUpdateFlightNumber
   - Description: Updates a certain flight number
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightNumber/{id}"
   - Request Type: PUT
   - Body parameters: String flightNumber
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??

2. adminUpdateFlightFare
   - Description: Updates a certain flight fare
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightFare/{id}"
   - Request Type: PUT
   - Body parameters: Integer fare
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??

3. adminUpdateFlightOrigin
   - Description: Updates a certain flight origin place
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightOrigin/{id}"
   - Request Type: PUT
   - Body parameters: String origin
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??

4. adminUpdateFlightDest
   - Description: Updates a certain flight destination place
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightDest/{id}"
   - Request Type: PUT
   - Body parameters: String dest
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??

5. adminUpdateFlightDepartureDate
   - Description: Updates a certain flight departure date
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/adminUpdateFlightDepartureDate/{id}"
   - Request Type: PUT
   - Body parameters: LocalDate departureDate
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??

6. adminUpdateFlightArrivalDate
   - Description: Updates a certain flight arrival date
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightArrivalDate/{id}"
   - Request Type: PUT
   - Body parameters: LocalDate arrivalDate
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??

## Removing a Flight
1. adminRemoveFlight
   - Description: delete a certain flight from DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/removeFlight/{id}"
   - Request Type: DELETE
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: N/A
   - Example: ??

## Flight APIs (Reading)
1. getAllFights
   - Description: returns all flights available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readAllFlights"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: N/A
   - Returns: List of flights
   - Example: ??

2. readFlightById
   - Description: returns all flights available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlight/{id}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: Integer id
   - Returns: single flight if exists
   - Example: ??

3. getFlightsByAirLine
   - Description: returns all flights that belong to the same air line in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readAllFlightsByAirLine/{airLine}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: String airLine
   - Returns: List of flights
   - Example: ??

4. getFlightsByOrigin
   - Description: returns all flights that have the same departure place in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightsByOrigin/{from}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: String from
   - Returns: List of flights
   - Example: ??

5. getFlightsByDestination
   - Description: returns all flights that have the same destination available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightByOrigin/{to}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: String to
   - Returns: List of flights
   - Example: ??

6. getFlightByDepartureDate
   - Description: returns all flights that have the same departure timestamp available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightByDepartureDate/{departureDate}"
   - Request Type: GET
   - Body parameters: LocalDate departureDate
   - Path parameters: N/A
   - Returns: List of flights
   - Example: ??

7. getFlightByArrivalDate
   - Description: returns all flights that have the same arrival timestamp available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightByArrivalDate/{arrivalDate}"
   - Request Type: GET
   - Body parameters: LocalDate arrivalDate
   - Path parameters: N/A
   - Returns: List of flights
   - Example: ??

8. getFlightByPriceRange
   - Description: returns all flights within a certain price range available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightByPriceRange/{minFare}/{maxFare}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: Integer minFare, Integer maxFare
   - Returns: List of flights
   - Example: ??

## Booking APIs(Reading)
1. getBooking
   - Description: return a single reservation record by Id
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Booking/readBooking/{id}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: Booking booking
   - Example: ??

2. getAllBookings
   - Description: return all reservation records by Id
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Booking/readAllBookings"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: N/A
   - Returns: List<Booking>
   - Example: ??

3. getAllCustomerBookings
   - Description: return all reservation records of certain customer by Id
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Booking/readAllCustomerBookings/{id}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: List<Booking>
   - Example: ??


# Milestones
1. Complete features ( Complete )
2. update readme.md
3. Refactor APIs
4. Add JWT to APIs
5. ??