# 1.1. Details about repo
This repository is created to store lab works for the SAD course. It will include completed labs and all the steps of development from start to finish.
# 1.2. Task details
This project aims to explore the development of a digital version of Rock, Paper, Scissors, focusing on the game's mechanics, implementation, and user experience. By utilizing contemporary programming languages and development tools, we will create an interactive game that captures the essence of this age-old contest while incorporating modern features such as graphical user interfaces, score tracking, and enhanced gameplay dynamics.
# 1.3. Student number and details
Student number: 10
Game: rock paper scissors
Config format: XML
# 1.4. Details about technology, program language, and HW
In this project i will use a client-server architecture. The client is an application that gets data from a server, represented by an Arduino board that performs all the calculations and returns the results to the app. This project will be developed in C/C++/C# or Java language. Final decision I will make in a process of development.


### Task 2 Instructions

  

#### Description of the System:

The client (console Java app) asks the user to enter a message which is then sent to the server. The server (which runs on Arduino) responds with a modified message.

  

#### Requirements:

  

##### Client:

- JDK 21 or higher

- Maven 3.6.3 or higher

  

##### Server:

- Arduino IDE 

--- 

### To Build and Run the Client (Java Application):

  

1. Fetch feature/develop/task2 branch
2. Navigate to csad2425ki403kononchuk10/client-rock-paper-scissors
3. Build project by typing mvn clean package
4. Run the application by mvn exec:java

  

### To Build and Run the Server (Arduino Sketch):

1. Navigate to csad2425ki403kononchuk10/server-rock-paper-scissors
2. Open file server.ino with Arduino IDE
3. Plug into the computer your Arduino board and select a port in the Arduino IDE
4. Program controller by pressing the upload button
