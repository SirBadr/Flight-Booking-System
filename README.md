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
1. As long as we have `customers` able to book a flight, upgrade or cancel a flight, we need a method to sign up/log in.

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
    String flightNumber
    Airline Airline
    Integer fare
    String from
    String to
    Date departureTime
    Date arrivalTime
    List<Seat> seats
}
```

```
class Airline {
    String Name
    List<Flight> allFlights
}
```

```
class Seat {
    String number
    Enum seatClass
}
```

```
Enum seatClass {
    Economy
    Business
}
```
below is an image of UML for the Flight class and it's associations
![Alt text](https://drive.google.com/file/d/118SneLB9w8AaRmX7OcceinXtN9npNPYV/view?usp=sharing "Flight UML")
