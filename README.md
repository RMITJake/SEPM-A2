# IT Service Desk Ticketing System
- Software Engineering Project Management Assessment 2
- An IT Service Desk ticketing system for a small startup, Cinco. 
<br/>It allows staff members to submit IT issues and technicians to manage and resolve the issues efficiently.


---
# Summary
- Developed using openjdk version "17.0.8" 2023-07-18.

## Files
As the project is pure Java and there is no scope to write SQL queries, data persistence will be handled through standalone files which will act in place of database tables.
<br/>Each file will be a list of records reflecting the structure of it's associated class.

---
# User Guide
## Prerequisites 
- OpenJDK 17.0.8 or a compatible version should be installed.
- Have an access to [RMIT's myDesktop](https://mydesktop.rmit.edu.au/).

## Running the program
Follow these steps to execute the IT Service Desk Ticketing System.

### Step 1: Download the ZIP file
1. Open ***Google Chrome*** and go to [SEPM-2 Github](https://github.com/RMITJake/SEPM-A2#compile-and-run-on-linux-using-the-build-script).
2. Sign in using your Github credentials.
3. Click `<> Code` and `Download ZIP` to download `SEPM-2-main.zip`.
4. Unzip the downloaded file in the ***Downloads*** folder. 

### Step 2: Run the Java codes
1. Open ***Eclipse*** >> ***File*** >> ***Open Projects from File System...***
2. Click ***Directory*** >> ***Downloads/SEPM-A2-main*** >> ***Open***
3. Click the `SEPM-A2-main` in ***Project Explorer*** and `Run App`.
<br/>![Screenshot1](https://github.com/RMITJake/SEPM-A2/blob/1a7c87126fa5b65ae0fc21d96013e39ac71f0d00/Screen%20Shot%2056.png) 

4. Java codes are successfully running!
<br/>![Screenshot1](https://github.com/RMITJake/SEPM-A2/blob/19046f501341baf7cbc340c663afd79487f6d12c/Screen%20Shot%2057.png) 

### Step 3: Execute the IT Service Desk Tikcketing System
1. Menu Options: 
    1. Upon starting, the program presents the following options:
        - `C` - Create a new user account.
        - `F` - Forgot password feature.
        - `L` - Log in to an existing account.
        - `E` - Exit the system.

2. Creating a User:
    1. Enter `C` to create a new user account.
    2. Enter your email address.
    3. Enter your full name.
    4. Enter your phone number.
    5. Enter a new password (including lowercase/uppercase/number).
    6. Confirm the entered password.
    7. Check the Account Summary and confirm it is correct `Y`.
    8. New account successfully created. Don't forget your account number and password!

3. Logging In:
    1. Enter `L` to log in to an existing account.
    2. Enter your account number.
    3. Enter your password.
    4. Logged in succesfully.
    5. The Main Menu presents the following options:
        - ***C*** - Create a new ticket
        - ***S*** - Select Ticket
        - ***M*** - My Open Tickets
        - ***L*** - Logout the account
        - ***E*** - Exit the system.
    6. Enter ***C*** to create a new ticket.
        1.


4. Forgot Password:
    1. Enter `F` to access the ***Forgot Password*** feature.
    2. Follow the prompts to reset your password.


5. Main Menu:
    1. If logged in successfully, you will access the main menu.
    2. Choose different options from the main menu as provided by the application.

6. Exiting the Application:
    1. To exit the application, Enter `E` at any point.


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
