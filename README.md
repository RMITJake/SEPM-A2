# IT Service Desk Ticketing System
- Software Engineering Project Management Assessment 2
- An IT Service Desk ticketing system for a small startup, Cinco. It allows staff members to submit IT issues and technicians to manage and resolve the issues efficiently.

---
# Summary
- Developed using openjdk version "17.0.8" 2023-07-18


## Files
As the project is pure Java and there is no scope to write SQL queries, data persistence will be handled through standalone files which will act in place of database tables.
Each file will be a list of records reflecting the structure of it's associated class.

---
# User Guide
## Prerequisites Running the program
- OpenJDK 17.0.8 or a compatible version should be installed.
- Have access to [RMIT's myDesktop](https://mydesktop.rmit.edu.au/).

## Running the program
Follow these steps to execute the IT Service Desk Ticketing System.

### Step 1: Access the Github
1. Open Google Chrome and go to [SEPM-2 Github](https://github.com/RMITJake/SEPM-A2#compile-and-run-on-linux-using-the-build-script).
2. Sign in using your Github credentials.
3. Click `<> Code` and `Download ZIP`.

### Step 2: Compile the Java codes

### Step 3: Run the Java codes
1. Menu Options: 
    1. Upon starting, the program presents the following options:
        1. "C" - Create a new user account.
        2. "F" - Forgot password feature.
        3. "L" - Log in to an existing account.

2. Creating a User:
    1. Choose "C" to create a new user account.
    2. Follow the prompts to provide necessary information.

3. Forgot Password:
    1. Choose "F" to access the "Forgot Password" feature.
    2. Follow the prompts to reset your password.

4. Logging In:
    1. Choose "L" to log in to an existing account.
    2. Enter your credentials (email and password).

5. Main Menu:
    1. If logged in successfully, you will access the main menu.
    2. Choose different options from the main menu as provided by the application.

6. Exiting the Application:
    1. To exit the application, choose "E" at any point.

### Compile and run on Linux using the build script
1. In a terminal cd into the project root directory.
2. In the terminal run the build.sh script in the project root directory.
3. Run the command "java App" to start the program.

### Compile and run on Linux manually
1. In a terminal cd into the project root directory.
2. Run the command "javac *.java src/controllers/*.java src/handlers/*.java src/models/*.java src/views/*.java"
3. Run the command "java App" to start the program.

### Compile and run on Windows using Eclipse

## Using the program
### As A Customer

### As A Technician
 
---
# Deliverables:
- Console based Java application
- Ticketing System
---
# Limitations
- Compatible with RMIT MyDesktop service
- JAVA only
- No SQL
- Data persistence, using .csv files for persistence, risky in the case of multiple write attempts to an already open file.
---
