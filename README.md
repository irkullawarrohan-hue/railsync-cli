Project Name
RailSync CLI

Project Overview
RailSync CLI is a Java based command line train booking application.
The project simulates a real world railway reservation system at the backend level.
It focuses on implementing booking logic, user flow, and data persistence without using any frameworks or UI libraries.

This project is built mainly for learning and strengthening backend fundamentals.

What This Project Does
The application allows users to sign up and log in.
Users can search trains using source and destination stations.
Seat availability is displayed and seats can be booked.
User bookings are stored and retrieved from persistent storage.
All operations are handled through a menu driven command line interface.

Feature Contribution Breakdown
User authentication and signup logic accounts for 25 percent of the project.
This includes password hashing, login validation, and user data storage.

Train search and route validation logic accounts for 25 percent of the project.
This includes matching source and destination, validating station order, and handling no train found cases.

Seat management and booking logic accounts for 30 percent of the project.
This includes seat availability display, booking validation, updating seat status, and saving changes.

Data persistence using JSON files accounts for 20 percent of the project.
This includes reading and writing user and train data using file based storage.

Technical Stack Used
The project is written using Java eight.
Gradle is used for build and dependency management.
Jackson library is used for JSON serialization and deserialization.
jBCrypt is used for secure password hashing.
The project is developed and run using IntelliJ IDEA.

Application Flow
When the application starts, a command line menu is displayed.
Users can choose options like signup, login, search trains, book seats, view bookings, or exit.

During signup, users enter a username and password.
The password is hashed and stored securely.

During login, credentials are validated against stored user data.
Only authenticated users can perform booking related actions.

When searching trains, users provide a source station and destination station.
If matching trains are available, they are shown.
If no trains are found, the application responds safely without errors.

During seat booking, users select a train and choose a seat.
The seat status is updated and saved immediately.

Data Persistence Approach
Instead of using a database, JSON files are used as a local data store.
User data and booking history are stored in a user JSON file.
Train details and seat availability are stored in a train JSON file.
This ensures data remains available even after restarting the application.

Learning Outcomes
This project helped me understand backend flow control and state management.
I learned how real world booking systems validate user actions.
I improved my debugging skills by handling runtime errors and data mismatches.
I gained confidence in structuring Java applications cleanly.

Project Purpose
This project is built mainly for learning and practice.
It focuses 100 percent on backend logic and problem solving rather than UI or frameworks.
