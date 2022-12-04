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

# How To Run Project
1. create database with name "flightbooking" and give all privileges to your user and postgres you can use commands below 
   ```
   $ psql
   $ CREATE DATABASE flightbooking;
   $ GRANT ALL PRIVILEGES ON DATABASE "flightbooking" TO {your_user};
   $ GRANT ALL PRIVILEGES ON DATABASE "flightbooking" TO postgres;
   ```
   
2. Run the spring boot code
   - if you are using `mvn` CLI: simply navigate to project root and use the following command
      ```mvn spring-boot:run```
   - if you are using Intellij just navigate to "src/main/java/com.example.demo/DemoApplication" and just click the green arrow next to the demo application class

# How to Run Project JUnit test
1. if you're using `mvn` CLI: just type ```mvn test``` to run all test cases
2. or just use Intellij to run specific test cases in "src/test/java/com.example.demo" and you can see there different test cases

# Project scope
This project is a flight booking system will allow admin to add/update/read/remove flights and specifying their details like fare, destination, origin, departure and arrival times.
And will allow customers to search through all flights, by fare, from and to destinations, book a certain flight ticket, upgrade to a higher class, cancel his booking.

Customer's payment for a flight is considered out-of-scope of this project, so payments will not be handled.

## Assumptions.
1. As long as we have `customers` able to book a flight, upgrade or cancel a flight, we need a method to log in.
2. Handling a trivial sign up / sign in only with email ( without password ).

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
   ```
   $ psql
   $ CREATE DATABASE flightbooking;
   $ GRANT ALL PRIVILEGES ON DATABASE "flightbooking" TO *;
   $ GRANT ALL PRIVILEGES ON DATABASE "flightbooking" TO postgres;
   ```

# APIs
You'll find the postman API documentation in this link
[I'm an inline-style link](https://documenter.getpostman.com/view/17722703/2s8YzMXk2X)

## Roles (APIs to create roles / add roles to system actors (i.e. admin, customer))
1. saveRole
   - Description: Creates a new role
   - EndPoint: "http://localhost:8080/api/v1/Roles/saveRole"
   - Request Type: POST
   - Body parameters: Role role
   - Path parameters: N/A
   - Returns: Role
   - Example: 
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Roles/saveRole' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name":"ADMIN_ROLE"
   }'
   ```

2. addRoleToCustomer
   - Description: adds a new role to customer
   - EndPoint: "http://localhost:8080/api/v1/Roles/addRoleToCustomer/{id}"
   - Request Type: POST
   - Body parameters: Role role
   - Path parameters: id (customer id)
   - Returns: N/A
   - Example: 
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Roles/addRoleToCustomer/1' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name":"ROLE_CUSTOMER"
   }'
   ```

3. addRoleToAdmin
   - Description: adds a new role to admin
   - EndPoint: "http://localhost:8080/api/v1/Roles/addRoleToAdmin"
   - Request Type: POST
   - Body parameters: Role role
   - Path parameters: id (admin id)
   - Returns: N/A
   - Example:
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Roles/addRoleToAdmin/1' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name":"ADMIN_ROLE"
   }'
   ```

4. getCustomerRoles
   - Description: read all roles of a certain customer
   - EndPoint: "http://localhost:8080/api/v1/Roles/customerRoles/{id}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: id (customer id)
   - Returns: Collection<Role>
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Roles/customerRoles/1'
   ```

## Customer APIs
1. Sign Up
   - Example:
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Customers/signUp' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name":"Mahmoud",
   "email":"mahmoudbadr9199@gmail.com",
   "birthDate":199991,
   "gender":"MALE"
   }'
   ```
2. Sign In
   - Example:
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Customers/signIn' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "email":"mahmoudbadr9199@gmail.com"
   }'
   ```
## Customer Flight Booking APIs
1. bookFlight
   - Description: Books a new flight to a customer
   - EndPoint: "http://localhost:8080/api/v1/Booking/bookFlight"
   - Request Type: POST
   - Body parameters: Booking booking
   - Path parameters: N/A
   - Returns: N/A
   - Example:
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Booking/bookFlight' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "flightId":1,
   "seatNumber":"AA12",
   "seatType":"ECONOMY",
   "customerId":1
   }'
   ```

2. upgradeSeat
   - Description: upgrades a reserved seat to Business class
   - EndPoint: "http://localhost:8080/api/v1/Booking/upgradeSeat/{id}"
   - Request Type: PUT
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: N/A
   - Example:
   ```
   curl --location --request PUT 'http://localhost:8080/api/v1/Booking/upgradeSeat/1'
   ```

3. downgradeSeat
   - Description: downgrades a reserved seat to Economy class
   - EndPoint: "http://localhost:8080/api/v1/Booking/downgradeSeat/{id}"
   - Request Type: PUT
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: N/A
   - Example:
   ```
   curl --location --request PUT 'http://localhost:8080/api/v1/Booking/downgradeSeat/1'
   ```


## Admin APIs
1. Sign Up
   - Example:
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Admins/signUp' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name":"MahmoudAdmin",
   "email":"admin@admin.com"
   }'
   ```
2. Sign In
   -Example:
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Admins/signIn' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "email":"admin@admin.com"
   }'
   ```

### Admin APIs for Flights
## Adding a Flight
1. adminAddFlight (Requires: ADMIN_ROLE)
   - Description: adds a new flight to DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/addFlight/{token}"
   - Request Type: POST
   - Body parameters: Flight
   - Path parameters: String token
   - Returns: N/A
   - Example:
   ```
   curl --location --request POST 'http://localhost:8080/api/v1/Admins/addFlight/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJyb2xlcyI6WyJBRE1JTl9ST0xFIl19.KNtxJ5HaUwo76Ptq77naxHvCaemPDPFOlFkwsgvZAPo' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "flightNumber":"12A",
   "airLine":"QATARAIRWAYS",
   "fare":124,
   "origin":"ALX",
   "dest":"SYD",
   "departureDate":124234,
   "arrivalDate":124500,
   "departureTime":124352,
   "arrivalTime":124400
   }'
   ```

## Updating a Flight
1. adminUpdateFlightNumber (Requires: ADMIN_ROLE)
   - Description: Updates a certain flight number
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightNumber/{id}/{token}"
   - Request Type: PUT
   - Body parameters: String flightNumber
   - Path parameters: Long id, String token
   - Returns: N/A
   - Example:
   ```
   curl --location --request PUT 'http://localhost:8080/api/v1/Admins/updateFlightNumber/1/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJyb2xlcyI6WyJBRE1JTl9ST0xFIl19.KNtxJ5HaUwo76Ptq77naxHvCaemPDPFOlFkwsgvZAPo' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "flightNumber":"12B"
   }'
   ```

2. adminUpdateFlightFare (Requires: ADMIN_ROLE)
   - Description: Updates a certain flight fare
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightFare/{id}/{token}"
   - Request Type: PUT
   - Body parameters: Integer fare
   - Path parameters: Long id, String token
   - Returns: N/A
   - Example: 
   ```
   curl --location --request PUT 'http://localhost:8080/api/v1/Admins/updateFlightFare/1/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJyb2xlcyI6WyJBRE1JTl9ST0xFIl19.KNtxJ5HaUwo76Ptq77naxHvCaemPDPFOlFkwsgvZAPo' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "fare":991
   }'
   ```

3. adminUpdateFlightOrigin (Requires: ADMIN_ROLE)
   - Description: Updates a certain flight origin place
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightOrigin/{id}/{token}"
   - Request Type: PUT
   - Body parameters: String origin
   - Path parameters: Long id, String token
   - Returns: N/A
   - Example: 
   ```
   curl --location --request PUT 'http://localhost:8080/api/v1/Admins/updateFlightOrigin/1/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJyb2xlcyI6WyJBRE1JTl9ST0xFIl19.KNtxJ5HaUwo76Ptq77naxHvCaemPDPFOlFkwsgvZAPo' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "origin":"TAI"
   }'
   ```

4. adminUpdateFlightDest (Requires: ADMIN_ROLE)
   - Description: Updates a certain flight destination place
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightDest/{id}/{token}"
   - Request Type: PUT
   - Body parameters: String dest
   - Path parameters: Long id, String token
   - Returns: N/A
   - Example: 
   ```
   curl --location --request PUT 'http://localhost:8080/api/v1/Admins/updateFlightDest/1/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJyb2xlcyI6WyJBRE1JTl9ST0xFIl19.KNtxJ5HaUwo76Ptq77naxHvCaemPDPFOlFkwsgvZAPo' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "dest":"USA"
   }'
   ```

5. adminUpdateFlightDepartureDate (Requires: ADMIN_ROLE)
   - Description: Updates a certain flight departure date
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/adminUpdateFlightDepartureDate/{id}/{token}"
   - Request Type: PUT
   - Body parameters: LocalDate departureDate
   - Path parameters: Long id, String token
   - Returns: N/A
   - Example:
   ```
   curl --location --request PUT 'http://localhost:8080/api/v1/Admins/updateFlightDepartureDate/1/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJyb2xlcyI6WyJBRE1JTl9ST0xFIl19.KNtxJ5HaUwo76Ptq77naxHvCaemPDPFOlFkwsgvZAPo' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "departureDate":12435
   }'
   ```

6. adminUpdateFlightArrivalDate (Requires: ADMIN_ROLE)
   - Description: Updates a certain flight arrival date
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/updateFlightArrivalDate/{id}/{token}"
   - Request Type: PUT
   - Body parameters: LocalDate arrivalDate
   - Path parameters: Long id, String token
   - Returns: N/A
   - Example:
   ```
   curl --location --request PUT 'http://localhost:8080/api/v1/Admins/updateFlightArrivalDate/1/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJyb2xlcyI6WyJBRE1JTl9ST0xFIl19.KNtxJ5HaUwo76Ptq77naxHvCaemPDPFOlFkwsgvZAPo' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "arrivalDate":29321
   }'
   ```

## Removing a Flight
1. adminRemoveFlight (Requires: ADMIN_ROLE)
   - Description: delete a certain flight from DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Admins/removeFlight/{id}/{token}"
   - Request Type: DELETE
   - Body parameters: N/A
   - Path parameters: Long id, String token
   - Returns: N/A
   - Example:
   ```
   curl --location --request DELETE 'http://localhost:8080/api/v1/Admins/removeFlight/1/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJyb2xlcyI6WyJBRE1JTl9ST0xFIl19.KNtxJ5HaUwo76Ptq77naxHvCaemPDPFOlFkwsgvZAPo'
   ```

## Flight APIs (Reading)
1. getAllFights
   - Description: returns all flights available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readAllFlights"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: N/A
   - Returns: List of flights
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Flight/readAllFlights'
   ```

2. readFlightById
   - Description: returns all flights available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlight/{id}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: Integer id
   - Returns: single flight if exists
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Flight/readFlight/1'
   ```

3. getFlightsByAirLine
   - Description: returns all flights that belong to the same air line in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readAllFlightsByAirLine/{airLine}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: String airLine
   - Returns: List of flights
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Flight/readAllFlightsByAirLine/QATARAIRWAYS'
   ```

4. getFlightsByOrigin
   - Description: returns all flights that have the same departure place in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightsByOrigin/{from}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: String from
   - Returns: List of flights
   - Example: 
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Flight/readFlightByOrigin/ALX'
   ```

5. getFlightsByDestination
   - Description: returns all flights that have the same destination available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightByOrigin/{to}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: String to
   - Returns: List of flights
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Flight/readFlightByDestination/SYD'
   ```

6. getFlightByDepartureDate
   - Description: returns all flights that have the same departure timestamp available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightByDepartureDate/{departureDate}"
   - Request Type: GET
   - Body parameters: LocalDate departureDate
   - Path parameters: N/A
   - Returns: List of flights
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Flight/readFlightByDepartureDate' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "departureDate":124234
   }'
   ```

7. getFlightByArrivalDate
   - Description: returns all flights that have the same arrival timestamp available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightByArrivalDate/{arrivalDate}"
   - Request Type: GET
   - Body parameters: LocalDate arrivalDate
   - Path parameters: N/A
   - Returns: List of flights
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Flight/readFlightByArrivalDate' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "arrivalDate":124500
   }'
   ```

8. getFlightByPriceRange
   - Description: returns all flights within a certain price range available in DB
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Flight/readFlightByPriceRange/{minFare}/{maxFare}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: Integer minFare, Integer maxFare
   - Returns: List of flights
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Flight/readFlightByPriceRange/100/150'
   ```

## Booking APIs(Reading)
1. getBooking
   - Description: return a single reservation record by Id
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Booking/readBooking/{id}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: Booking booking
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Booking/readBooking/1'
   ```

2. getAllBookings
   - Description: return all reservation records by Id
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Booking/readAllBookings"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: N/A
   - Returns: List<Booking>
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Booking/readAllBookings'
   ```

3. getAllCustomerBookings
   - Description: return all reservation records of certain customer by Id
   - Permission: public (customer or admin)
   - EndPoint: "http://localhost:8080/api/v1/Booking/readAllCustomerBookings/{id}"
   - Request Type: GET
   - Body parameters: N/A
   - Path parameters: Long id
   - Returns: List<Booking>
   - Example:
   ```
   curl --location --request GET 'http://localhost:8080/api/v1/Booking/readAllCustomerBookings/1'
   ```


# Milestones
1. Complete features ( Complete )
2. update readme.md ( Complete )
3. Refactor APIs - Exception Handling - ( Complete )
4. Add JUnit testing ( Complete )
5. Add JWT to APIs ( Complete )
6. add API validation ( In Progress )