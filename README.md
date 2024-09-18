Group Member: Anh Tran, Joshua Mack, Justin Dam, Vats Panchal

We will be working on a Hotel management system. From our initial discussion, we have decided for classes we will have Hotel Class, Guest Class, Employee Class, Room Abstract class, Payment class(Employee Paying subclass & Guest Paying subclass), Amenity Abstract class, Reservable(Interface).

The Guest class represents individuals staying at the hotel. It stores guest information and handles operations like making and canceling reservations. Also store guest information for future visits, keeping track of their data. Includes Arrival and Departure Date so that the same room can not be booked by different parties (Possibly a Booking subclass to deal with this). 

Employee Class: contains hours worked, methods for modifying reservations (amenities and rooms that implement reservable), wage/salary information, name, employee ID, and position

Hotel Class: defines the hotel's lists of rooms, guests, employees, and a quick data structure (i.e. dictionary or hashmap) to keep track of what amenities it offers 

Room (abstract) Class: serves as a holder for different types of rooms, with different features. Room objects will contain guests, methods to create/modify reservations, and a log of amenities ordered that gets updated by the appropriate methods.

Payment (abstract) Class: manages the financial transactions for the hotel, including charges for guest stays, amenities, and services.
Subclass EmployeePayment will deal with employee paychecks, hours worked, and modifications to their attributes
Subclass GuestPayment will calculate guest charges for rooms and amenities and redemptions of points

Amenity (abstract): represents various attractions offered by the hotel. Can be reserved.
	Subclasses (gym, pool, conference rooms, spa, etc.) to be added

Reservable Interface: defines methods for reserving and canceling reservations. Both rooms and amenities that can be reserved must implement this interface.


