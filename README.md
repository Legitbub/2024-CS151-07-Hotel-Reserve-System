# Overview
The Hotel Reserve System is a comprehensive hotel management solution designed to streamline guest reservations, employee management, room and amenity bookings, and payment processing. The system is built using object-oriented principles and includes various classes and interfaces to handle different hotel operations efficiently

# Table of Contents
1. [Features](#Features)
2. [Installation](#installation-instructions)
3. [Project Structure](#project-structure)
4. [Contribution](#contributions)
5. [Acknowledgments](#acknowledgments)

# Features:

### 1. Room Management:


	Room Reservation: The system provides the ability to reserve, release, and check the availability of rooms. 
	Rooms are classified into different types:

		Standard Room: A basic room option available for reservation with attributes like room size, cost, and availability.

		VIP Room: A premium room option with additional features and a higher cost.

	The system ensures that room status (available or reserved) is consistently tracked and managed.
### 2. Amenity Management:


	Amenity Booking: Guests can book various amenities offered by the hotel, including:

		Gym: Provides access to workout equipment and health services. The gym’s availability, equipment usage, and maintenance schedules are handled by the system.

		Pool: Guests can book time slots to use the swimming pool. The system manages pool availability, temperature settings, and maintenance schedules.

		Restaurant: Manages seat reservations, menu options, and food services. Guests can reserve tables and enjoy the dining facilities.
### 3. Guest Management:

	Guest Profiles: The system manages guest information, including guest ID, contact details, and reservation history. 

	Guests can:
		
		Make Reservations: Book rooms and amenities directly through the system.
		Pay Bills: Guests can process payments for room bookings, amenities, and additional services through the integrated payment system.
### 4. Employee Management:

	Employee Profiles: The system tracks employee details, including employee ID, job titles, and salary. 

	Employees can:

		Clock In/Clock Out: Track working hours.
		Perform Assigned Duties: Log daily tasks and responsibilities.

	Manager Role: In addition to regular employee functions. 
	Managers can:

		Approve Leave Requests: Handle employee requests for time off.
		Monitor Performance: Track employee performance, Financial Accounts and provide feedback or  evaluations.
### 5. Payment System:

	Guest Payment: A secure system for processing guest payments, including room charges, amenity bookings, and additional fees.

	Employee Payment: The system manages payroll for employees, ensuring timely and accurate payment of salaries based on working hours and role.
### 6. Reservation System:

	The system implements a unified reservation process for both rooms and amenities through the Reservable Interface, which ensures consistent methods for booking, releasing, and managing reservations.

# Installation Instructions:
To set up the project locally:

Clone the repository:
```rb
git clone https://github.com/vatspanchal/2024-CS151-07-Hotel-Reserve-System.git
```

Navigate to the project directory:
```rb
cd 2024-CS151-07-Hotel-Reserve-System
```

Run the project:
```rb
 javac UI.java
 ```


# Project Structure: 
The project is organized into the following classes and interfaces, reflecting a modular design to handle hotel operations such as reservations, employee management, and amenity services.

### 1. **Room Management**
   - **Room (Abstract)**: The base class for all rooms, containing common attributes like `roomNumber`,and `cost`.
     - **StandardRoom**: Inherits from `Room`. Represents a standard room type with basic features.
     - **VIPRoom**: Inherits from `Room`. Represents a premium room type with additional luxury features.
   - **Key Methods**: `reserve()`, `compareTo()`,

### 2. **Amenity Management**
   - **Amenity (Abstract)**: The base class for hotel amenities, providing attributes like `name`, `cost`, `availability`, and `schedule`.
     - **Gym**: Inherits from `Amenity`. Manages access to the hotel's workout facilities.
     - **Pool**: Inherits from `Amenity`. Manages pool services such as availability and water temperature.
     - **Restaurant**: Inherits from `Amenity`. Manages dining services, including table reservations and menu options.
   - **Key Methods**: `reserve()`, `cancel()`, `getAvailability()`, 

### 3. **Guest and Employee Management**
   - **Guest**: Handles guest-related operations such as reservations, and bill payments.
     - Attributes: `guestID`, `name`, `contactDetails`
     - **Key Methods**: `displayGuestAccount()`, `checkout()`
   - **Employee**: Represents hotel employees, tracking work hours and tasks.
     - Attributes: `employeeID`, `name`, `role`, `salary`
   - **Manager**: Inherits from `Employee`, providing additional managerial functions.

### 4. **Payment System**
   - **Payment (Abstract)**: The base class for handling payments.
     - **GuestPayment**: Inherits from `Payment`, handling guest transactions for rooms and amenities.
     - **EmployeePayment**: Inherits from `Payment`, managing payroll and salaries for employees.
   - **Key Methods**: `processPayment()`, `refund()`, `generateInvoice()`

### 5. **Reservation System**
   - **Reservable (Interface)**: Implemented by any class that represents a reservable entity, such as rooms or amenities. Provides methods to handle the reservation process.
     - **Key Methods**: `reserve()`, `cancel()`

# Contributions: 
### Justin Dam - `UI` , `Hotel`, `Manager implementation`
### Vats Panchal - `Payment class`, `Employee Payment`, `Guest Payment` & `Readme`
### Anh Tran: `UML`, `JUnit test`, `Manager Class` & `Room Class`

# Acknowledgments:
This project was developed as part of the CS151 course at San Jose State University. Special thanks to Professor Telvin Zhong for his guidence and suggestions.
